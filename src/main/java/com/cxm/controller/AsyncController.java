package com.cxm.controller;

import com.cxm.entity.EmployeeAddresses;
import com.cxm.entity.EmployeeDTO;
import com.cxm.entity.EmployeeName;
import com.cxm.entity.EmployeePhones;
import com.cxm.service.AsyncEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {


    @Autowired
    private AsyncEmployeeService asyncService;

    @GetMapping("/profile/info")
    public EmployeeDTO infos() throws Exception {
        CompletableFuture<EmployeePhones> phones = asyncService.phones();
        CompletableFuture<EmployeeName> names = asyncService.names();
        CompletableFuture<EmployeeAddresses> addresses = asyncService.addresses();
        CompletableFuture.allOf(phones,names,addresses).join();
        System.out.println(names.get());
        return new EmployeeDTO(Arrays.asList(names.get()), Arrays.asList(addresses.get()), Arrays.asList(phones.get())) ;
    }
}
