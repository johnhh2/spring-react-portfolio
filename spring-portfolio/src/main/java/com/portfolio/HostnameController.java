package com.portfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONObject;

import com.portfolio.User;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/portfolio",
                produces=MediaType.APPLICATION_JSON_VALUE)
public class HostnameController {
    @Autowired
    private HostnameRepository hostnameRepository;

    @GetMapping("get/all")
    public String getAllHostnames() {
        Iterable<Hostname> allHostnames =  hostnameRepository.findAll();
        JSONObject object = new JSONObject();
        for (Hostname hostname : allHostnames) {
            object.put(hostname.getName(), hostname.getAccount().toString());
        }
        return object.toString();
    }

    @GetMapping("get")
    public String get_portfolio() {
        /* TODO: get current hostname */
        String hostname = "localhost";

        JSONObject object = new JSONObject();

        // TODO: Query Hostnames to find user of the hostnames portfolio
        Hostname host = hostnameRepository.findByName(hostname);
        if (host != null) {
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

        return object.toString();
    }

}
