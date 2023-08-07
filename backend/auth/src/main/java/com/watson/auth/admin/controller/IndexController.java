package com.watson.auth.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

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
