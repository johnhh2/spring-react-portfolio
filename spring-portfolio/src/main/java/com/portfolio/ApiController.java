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
    private UserRepository user_repository;

    @Autowired
    private HostnameRepository hostname_repository;

    @Autowired
    private PortfolioRepository portfolio_repository;

    @Autowired
    private PortfolioCategoryRepository portfolio_category_repository;

    @GetMapping("get_user")
    public String get_user(@RequestParam int id) {
        // TODO: Validate the id
        Optional<User> user = user_repository.findById(id);
        if (user.isPresent()) {
            return user.get().toString();
        }
        return null;
    }

    @GetMapping("get_users")
    public String get_users() {
        Iterable<User> all_users =  user_repository.findAll();
        String out = "{";
        for (User user : all_users) {
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
    public String create_user(@RequestBody Map<String, String> request_body) {
        String username = request_body.get("username");
        String email = request_body.get("email");
        int age = Integer.parseInt(request_body.get("age"));
        // TODO: Add validation for fields
        User user = new User(username, email, age);
        user_repository.save(user);
        // TODO: Return success: false on error
        return "{ \"success\": true, \"user\": " + user.toString() + "}";
    }

    @GetMapping("portfolio/get")
    public String get_portfolio(@RequestParam String id) {
        // TODO: Validate the id
        Optional<Hostname> result = hostname_repository.findById(id);
        if (result.isPresent()) {
            Hostname hostname = result.get();
            Portfolio portfolio = hostname.getPortfolio();
            String realname = portfolio.getRealname();
            Iterable<PortfolioCategory> categories = portfolio_category_repository.findAll();
            for (PortfolioCategory category : categories) {
                if (category.getPortfolio().getRealname().equals(realname)) {
                    portfolio.addCategory(category);
                }
            }
            return portfolio.toString();
        }
        return null;
    }

    @GetMapping("portfolio/get/all")
    public String get_all_hostnames() {
        Iterable<Hostname> all_hostnames =  hostname_repository.findAll();
        JSONObject object = new JSONObject();
        for (Hostname hostname : all_hostnames) {
            object.put(hostname.getName(), hostname.getPortfolio().toString());
        }
        return object.toString();
    }

    @PostMapping("portfolio/create")
    @Transactional
    public String create_dummy_portfolio(@RequestBody Map<String, String> request_body) {
        String realname = request_body.get("realname");

        Portfolio portfolio = new Portfolio(realname);

        PortfolioCategory category = new PortfolioCategory(
            "Mobile Applications", "phone_iphone");
        portfolio.addCategory(category);
        portfolio_category_repository.save(category);

        User user = user_repository.findById(1).get();

        Hostname hostname = new Hostname(realname, user, portfolio);
        hostname_repository.save(hostname);
        return "{\"success\": true, \"portfolio\": " + portfolio.toString() + "}";
    }

}
