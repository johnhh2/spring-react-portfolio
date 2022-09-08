package com.portfolio;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApiController {

    public JSONResponse response = new JSONResponse(0);
    public Map<Integer, User> users = new HashMap<Integer, User>();

    @RequestMapping(value="/", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String json() {
        return this.response.toString();
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String edit(@RequestBody Map<String, Integer> request_body) {
        int num = request_body.get("increment");
        this.response.increment_by(num);
        return this.response.toString();
    }

    @RequestMapping(value="/get_user", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_user(@RequestParam int key) {
        // TODO: Validate the key
        User user = this.users.get(key);
        if (user != null) {
            return user.toString();
        }
        return null;
    }

    @RequestMapping(value="/get_users", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_users() {
        String out = "{";
        for (Map.Entry<Integer, User> entry: this.users.entrySet()) {
            out += "\"" + entry.getKey() + "\": " + entry.getValue().toString() + ", ";
        }
        if (out.length() > 2) {
            out = out.substring(0, out.length() - 2); // Remove trailing ", "
        }
        out += "}";
        return out;
    }

    @RequestMapping(value="/create_user", method=RequestMethod.POST,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String create_user(@RequestBody Map<String, String> request_body) {
        int key = Integer.parseInt(request_body.get("key"));
        String username = request_body.get("username");
        String email = request_body.get("email");
        int age = Integer.parseInt(request_body.get("age"));
        // TODO: Add validation for fields
        User user = new User(key, username, email, age);
        this.users.put(key, user);
        // TODO: Return success: false on error
        return "{ \"success\": true, \"user\": " + user.toString() + "}";
    }

}
