package com.securityDevelopment.user.controller;

import com.securityDevelopment.user.model.UserModel;
import com.securityDevelopment.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    @Autowired
    UserService userService;

}
