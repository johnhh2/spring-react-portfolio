package com.portfolio;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ApiController {

    private class JSONResponse {

        private int key;

        public JSONResponse(int val) {
            this.key = val;
        }

        public int increment_by(int val) {
            this.key += val;
            return this.key;
        }

        public String toString() {
            return "{\"key\": " + this.key + "}";
        }
    }


    public JSONResponse response = new JSONResponse(0);
    public Map<Integer, User> users = new HashMap<Integer, User>();

    @RequestMapping(value="/api", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String json() {
        return this.response.toString();
    }

    @RequestMapping(value="/api/edit", method=RequestMethod.POST,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String edit(@RequestBody Map<String, Integer> request_body) {
        int num = request_body.get("increment");
        this.response.increment_by(num);
        return this.response.toString();
    }

    @RequestMapping(value="/api/get_user", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_user(@RequestParam int key) {
        // TODO: Validate the key
        return this.users.get(key).toString();
    }

    @RequestMapping(value="/api/create_user", method=RequestMethod.POST,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String create_user(@RequestBody Map<String, String> request_body) {
        int key = Integer.parseInt(request_body.get("key"));
        String username = request_body.get("username");
        String email = request_body.get("email");
        int age = Integer.parseInt(request_body.get("age"));
        // TODO: Add validation for fields
        User user = new User(key, username, email, age);
        this.users.put(key, user);
        return "{ \"success\": true, \"user\": " + user.toString() + "}";
    }

}
