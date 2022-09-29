package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.userDTO.SetUserProfileDTO;
import com.example.BackendVolatile.service.UserService;
import com.example.BackendVolatile.dto.userDTO.LoginDTO;
import com.example.BackendVolatile.dto.userDTO.RegisterDTO;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.userVO.GetUserDataVO;
import com.example.BackendVolatile.vo.userVO.LoginVO;
import com.example.BackendVolatile.vo.userVO.RegisterVO;
import com.example.BackendVolatile.vo.userVO.SetUserProfileVO;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Resource
    UserService userService;


    @GetMapping(value = "/login")
    public LoginVO login(@Valid LoginDTO loginDTO){
        return userService.userLogin(loginDTO);

    }

    @GetMapping(value = "/register")
    public RegisterVO register(@Valid RegisterDTO registerDTO){
        System.out.println("Register Controller");
        return userService.userRegister(registerDTO);
    }

    @PostMapping(value = "/setUserProfile")
    @UserLoginToken
    public SetUserProfileVO setUserProfile(@Valid @RequestBody SetUserProfileDTO setUserProfileDTO){
        return userService.setUserProfile(setUserProfileDTO);
    }

    @GetMapping(value = "/getUserData")
    @UserLoginToken
    public GetUserDataVO getUserData(){
        return userService.getUserData();
    }



    @Profile("test")
    @GetMapping(value = "/test_register")
    public RegisterVO test_register(@RequestParam("phone_number") String phone_number, @RequestParam("password") String password, @RequestParam("role")int role, @RequestParam("nick_name") String nick_name  )
    {
        RegisterDTO  registerDTO = new RegisterDTO();
        registerDTO.setPhone_number(phone_number);
        registerDTO.setPassword(password);
        registerDTO.setRole(role);
        registerDTO.setNickname(nick_name);
        System.out.println("Register Controller");
        return userService.userRegister(registerDTO);
    }

    @Profile("test")
    @GetMapping(value = "/hello")
    public String test_hello()
    {
        return "hello";
    }





}
