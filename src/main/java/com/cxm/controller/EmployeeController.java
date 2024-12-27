package com.cxm.controller;

import com.cxm.entity.EmployeeAddresses;
import com.cxm.entity.EmployeeName;
import com.cxm.entity.EmployeePhones;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {


    @GetMapping("/getAddresses")
    public EmployeeAddresses getAddresses() throws InterruptedException {
        EmployeeAddresses addresses = new EmployeeAddresses();
        addresses.setId("1");
        addresses.setAddress("12");
        Thread.sleep(1000);
        return addresses;
    }

    @GetMapping("/getNames")
    public EmployeeName getNames() throws InterruptedException {
        EmployeeName name = new EmployeeName();
        name.setId("1");
        name.setName("cxm");
        Thread.sleep(1500);
        return name;
    }

    @GetMapping("/getPhones")
    public EmployeePhones getPhones() throws InterruptedException {
        EmployeePhones phones = new EmployeePhones();
        phones.setId("1");
        phones.setPhone("1234567890");
        Thread.sleep(2000);
        return phones;
    }

}
