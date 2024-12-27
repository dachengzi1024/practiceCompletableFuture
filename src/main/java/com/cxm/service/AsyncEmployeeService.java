package com.cxm.service;

import com.cxm.entity.EmployeeAddresses;
import com.cxm.entity.EmployeeName;
import com.cxm.entity.EmployeePhones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncEmployeeService {
    private static Logger logger = (Logger) LoggerFactory.getLogger(AsyncEmployeeService.class);

    @Autowired
    public RestTemplate restTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeAddresses> addresses(){
        logger.info("getEmployeeAdresses start");
        EmployeeAddresses employeeAddresses = restTemplate.getForObject("http://localhost:8080/getAddresses", EmployeeAddresses.class);
        logger.info("employeeAddresses"+ employeeAddresses) ;
        logger.info("getEmployeeAdresses completed");
        return CompletableFuture.completedFuture(employeeAddresses);

    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeName> names(){
        logger.info("getEmployeenames start");
        EmployeeName employeeNames = restTemplate.getForObject("http://localhost:8080/getNames", EmployeeName.class);
        logger.info("employeenames"+ employeeNames) ;
        logger.info("getEmployeenames completed");
        return CompletableFuture.completedFuture(employeeNames);

    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeePhones> phones(){
        logger.info("getEmployeephones start");
        EmployeePhones employeePhones = restTemplate.getForObject("http://localhost:8080/getPhones", EmployeePhones.class);
        logger.info("employeePhones"+ employeePhones) ;
        logger.info("getEmployeePhones completed");
        return CompletableFuture.completedFuture(employeePhones);

    }
}
