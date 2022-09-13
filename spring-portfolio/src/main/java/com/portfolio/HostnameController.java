package com.portfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value="/api/portfolio")
public class HostnameController {
    @Autowired
    private HostnameRepository hostnameRepository;

    @RequestMapping(value="/get", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_portfolio() {
        /* TODO: get current hostname */
        String hostname = "localhost";

        JSONObject object = new JSONObject();

        /* TODO: Query Hostnames to find user of the hostnames portfolio */
        Hostname host = hostnameRepository.findByName(hostname);
	if (host != null) {
            User user = host.getUser();
            /* TODO: Gather categories and projects and add them to the JSON
	     * Account account = user.getAccount(); */

            JSONArray categories = new JSONArray();
            /* TODO: for (PortfolioCategory category : account.categories) { */
	    PortfolioCategory category = new PortfolioCategory("Mobile Applications", "phone_iphone");
            categories.put(category.toJson());
            JSONArray projects = new JSONArray();
            object.put("realname", "John Doe");
            object.put("categories", categories);
            object.put("projects", projects);
        }

        return object.toString();
    }

}
