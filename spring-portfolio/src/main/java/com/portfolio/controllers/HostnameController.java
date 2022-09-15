package com.portfolio.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.models.Account;
import com.portfolio.models.Hostname;
import com.portfolio.models.PortfolioCategory;
import com.portfolio.models.User;
import com.portfolio.repository.AccountRepository;
import com.portfolio.repository.HostnameRepository;
import com.portfolio.repository.PortfolioCategoryRepository;
import com.portfolio.repository.UserRepository;

import java.net.InetAddress;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/hostname",
                produces=MediaType.APPLICATION_JSON_VALUE)
public class HostnameController {
    @Autowired
    private HostnameRepository hostnameRepository;

    @GetMapping("get/all")
    public String getAllHostnames() {
        Iterable<Hostname> allHostnames =  hostnameRepository.findAll();
        JSONObject object = new JSONObject();
        for (Hostname hostname : allHostnames)
            object.put(hostname.getName(), hostname.getAccount().toJson());
        return object.toString();
    }

    @GetMapping("get")
    public String get_hostname() {
        // get current hostname
        String hostname = InetAddress.getLoopbackAddress().getHostName();


        JSONObject object = new JSONObject();

        // Query Hostnames to find user of the hostnames portfolio
        Hostname host = hostnameRepository.findByName(hostname);
        if (host != null) {
            if (host.getEnabled()) {
                User user = host.getUser();
                Account account = user.getAccount();

                JSONArray categories = new JSONArray();
                for (PortfolioCategory category : account.getCategories())
                    categories.put(category.toJson());
                JSONArray projects = new JSONArray();
                object.put("realname", account.getRealname());
                object.put("categories", categories);
                object.put("projects", projects);
            }
        }

        return object.toString();
    }

}
