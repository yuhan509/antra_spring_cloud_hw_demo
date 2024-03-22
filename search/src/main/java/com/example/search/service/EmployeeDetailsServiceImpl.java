package com.example.search.service;

import com.example.search.dto.DetailsDto;
import com.example.search.dto.EmployeeDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final RestTemplate restTemplate;

    private final String employeesUrl = "http://EMPLOYEE/employees";
    private final String detailsUrl = "http://DETAILS/details/port";

    @Autowired
    public EmployeeDetailsServiceImpl(RestTemplate rt) {
        this.restTemplate = rt;
    }

    @Override
    public EmployeeDto[] getEmployees() {
        return restTemplate.getForObject(employeesUrl, EmployeeDto[].class);
    }

    @Override
    public DetailsDto getDetails() {
//        try {
//            Thread.sleep(100);
//        } catch(Exception e) {
//        }
        return restTemplate.getForObject(detailsUrl, DetailsDto.class);

    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultEmployeeDetails")
    public DetailsDto getEmployeesDetails() {
        CompletableFuture<DetailsDto> cf1 = CompletableFuture.supplyAsync(this::getDetails);
        CompletableFuture<EmployeeDto[]> cf2 = CompletableFuture.supplyAsync(this::getEmployees);
        CompletableFuture.allOf(cf1, cf2).join();
        DetailsDto d = cf1.join();
        EmployeeDto[] es = cf2.join();
        d.setData(es);
        return d;
    }

    private DetailsDto getDefaultEmployeeDetails() {
        DetailsDto d = new DetailsDto();
        d.setData("TimeOUT!!!");
        return d;
    }

}
