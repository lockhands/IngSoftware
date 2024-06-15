package com.api.fintech.Controllers;

import com.api.fintech.Models.Client;
import com.api.fintech.Models.Company;
import com.api.fintech.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping()
    public List<Company> findAll(){

        return companyRepository.findAll();
    }
}
