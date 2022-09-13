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
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HostnameRepository hostnameRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PortfolioCategoryRepository portfolioCategoryRepository;

    @GetMapping("get_user")
    public String getUser(@RequestParam int id) {
        // TODO: Validate the id
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().toString();
        }
        return null;
    }

    @GetMapping("get_users")
    public String getUsers() {
        Iterable<User> allUsers =  userRepository.findAll();
        String out = "{";
        for (User user : allUsers) {
            out += "\"" + user.getId() + "\": " + user.toString() + ", ";
        }
        if (out.length() > 2) {
            out = out.substring(0, out.length() - 2); // Remove trailing ", "
        }
        out += "}";
        return out;
    }

    @PostMapping("create_user")
    @Transactional
    public String createUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String email = requestBody.get("email");
        int age = Integer.parseInt(requestBody.get("age"));
        // TODO: Add validation for fields
        User user = new User(username, email, age);
        userRepository.save(user);
        // TODO: Return success: false on error
        return "{ \"success\": true, \"user\": " + user.toString() + "}";
    }

    @GetMapping("portfolio/get")
    public String getAccount(@RequestParam String id) {
        // TODO: Validate the id
        Optional<Hostname> hostname = hostnameRepository.findById(id);
        if (hostname.isPresent()) {
            return hostname.get().getAccount().toString();
        }
        return null;
    }

    @GetMapping("portfolio/get/all")
    public String getAllHostnames() {
        Iterable<Hostname> allHostnames =  hostnameRepository.findAll();
        JSONObject object = new JSONObject();
        for (Hostname hostname : allHostnames) {
            object.put(hostname.getName(), hostname.getAccount().toString());
        }
        return object.toString();
    }

    @PostMapping("portfolio/create")
    @Transactional
    public String createDummyAccount(@RequestBody Map<String, String> requestBody) {
        String realname = requestBody.get("realname");

        User user = userRepository.findById(1).get();

        Account account = new Account(user, realname);

        PortfolioCategory category = new PortfolioCategory(
            "Mobile Applications", "phone_iphone");
        account.addCategory(category);
        portfolioCategoryRepository.save(category);

        Hostname hostname = new Hostname(realname, user, account);
        hostnameRepository.save(hostname);
        return "{\"success\": true, \"account\": " + account.toString() + "}";
    }

}
