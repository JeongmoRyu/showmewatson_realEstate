package com.watson.auth.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/test")
    public @ResponseBody String test() { return "test"; }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/signup/role")
    public String chooseRole() {
        return "signupRoleOption";
    }

    @GetMapping("/signup/user")
    public String userSignup() {
        return "signupRoleUser";
    }

    @GetMapping("/signup/realtor")
    public String realtorSignup() { return "signupRoleRealtor"; }

}
