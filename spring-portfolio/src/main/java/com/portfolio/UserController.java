package com.portfolio;

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


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PortfolioCategoryRepository portfolioCategoryRepository;

    @Autowired
    private HostnameRepository hostnameRepository;

    @GetMapping("get")
    public String getUser(@RequestParam int id) {
        // TODO: Validate the id
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().toJson().toString();
        }
        return null;
    }

    @GetMapping("get/all")
    public String getUsers() {
        Iterable<User> allUsers =  userRepository.findAll();
        JSONObject object = new JSONObject();
        for (User user : allUsers)
            object.put(String.valueOf(user.getId()), user.toJson());
        return object.toString();
    }

    @PostMapping("create")
    @Transactional
    public String createUser(@RequestBody Map<String, String> requestBody) {
        String realname = requestBody.get("name");
        String username = requestBody.get("username");
        String email = requestBody.get("email");
        boolean darkMode = Boolean.parseBoolean(requestBody.get("darkMode"));
        int age = Integer.parseInt(requestBody.get("age"));
        // TODO: Add validation for fields
        User user = new User(username, email, age);
        userRepository.save(user);

        Account account = new Account(user, realname, darkMode);
        accountRepository.save(account);

        PortfolioCategory category = new PortfolioCategory(
            "Mobile Applications", "phone_iphone");
        account.addCategory(category);
        portfolioCategoryRepository.save(category);

        String host;
        if (username.equals("mpurnell1") || username.equals("johnhh2"))
            host = "localhost";
        else
            host = username;

        Hostname hostname = new Hostname(host, user, account);
        hostnameRepository.save(hostname);
        // TODO: Return success: false on error
        JSONObject object = new JSONObject();
        object.put("success", true);
        object.put("user", user.toJson());
        return object.toString();
    }
}
