package com.portfolio.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.portfolio.models.Account;
import com.portfolio.models.Hostname;
import com.portfolio.models.PortfolioCategory;
import com.portfolio.repository.AccountRepository;
import com.portfolio.repository.HostnameRepository;
import com.portfolio.repository.PortfolioCategoryRepository;


import java.net.InetAddress;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/account",
                produces=MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HostnameRepository hostnameRepository;

    @PostMapping("edit")
    public String account_edit(@RequestParam long account_id, @RequestBody Map<Field, String> requestBody) {
        Account account = accountRepository.getById(account_id);
        for (Field key : requestBody.keySet()) {
            String value = requestBody.get(key);
            ReflectionUtils.setField(key, account, value);
        }
        JSONObject object = new JSONObject();
        object.put("success", true);
        object.put("message", "You successfully updated your account");

        return object.toString();
    }

}
