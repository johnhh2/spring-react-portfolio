package com.portfolio.controllers;

import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.models.User;
import com.portfolio.repository.UserRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get")
    public String getUser(@RequestParam long id) {
        // TODO: Validate the id
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().toJson().toString();
        }
        return "{}";
    }

    @GetMapping("get/all")
    public String getUsers() {
        Iterable<User> allUsers =  userRepository.findAll();
        JSONObject object = new JSONObject();
        for (User user : allUsers)
            object.put(String.valueOf(user.getId()), user.toJson());
        return object.toString();
    }

}
