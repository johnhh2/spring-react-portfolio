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
import com.portfolio.models.EditAccountRequest;
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
    public String editAccount(@RequestParam long userId, @RequestBody EditAccountRequest editAccountRequest) {
        Account account = accountRepository.getByUser_id(userId);
        String realname = editAccountRequest.getRealname();
        String hostnameName = editAccountRequest.getHostname();
        boolean hostnameEnabled = editAccountRequest.getHostnameEnabled();
        boolean darkMode = editAccountRequest.getDarkMode();

        Optional<Hostname> result = hostnameRepository.findByName(hostnameName);
        Hostname hostname;
        if (result.isPresent()) {
            hostname = result.get();
        } else {
            hostname = new Hostname(hostnameName);
        }
        hostname.setEnabled(hostnameEnabled);

        account.setRealname(realname);
        account.setHostname(hostname);
        account.setDarkMode(darkMode);

        accountRepository.save(account);

        JSONObject object = new JSONObject();
        object.put("success", true);
        object.put("message", "Your account has been successfully updated.");
        object.put("account", account.toJson());

        return object.toString();
    }

}
