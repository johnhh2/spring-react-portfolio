package com.portfolio;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONArray;
import org.json.JSONObject;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class HostnameController {
    /*@Autowired
    private HostnameRepository repo;*/

    @RequestMapping(value="/api/get_hostname", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_hostname(@RequestParam String hostname) {
        /* TODO: Query Hostnames to find user of the hostnames portfolio */
        /* repo.findById(hostname) */
        /* TODO: Gather categories and projects and add them to the JSON */
        PortfolioCategory category = new PortfolioCategory("Mobile Applications", "phone_iphone");
        JSONArray categories = new JSONArray();
        categories.put(category.toJson());
        JSONArray projects = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("realname", "John Doe");
        object.put("categories", categories);
        object.put("projects", projects);

        return object.toString();
    }

}
