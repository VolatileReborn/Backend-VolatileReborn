package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.userDTO.LoginDTO;
import com.example.BackendVolatile.dto.userDTO.RegisterDTO;
import com.example.BackendVolatile.dto.userDTO.SetUserProfileDTO;
import com.example.BackendVolatile.vo.userVO.GetUserDataVO;
import com.example.BackendVolatile.vo.userVO.LoginVO;
import com.example.BackendVolatile.vo.userVO.RegisterVO;
import com.example.BackendVolatile.vo.userVO.SetUserProfileVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public interface UserService {

    LoginVO userLogin(LoginDTO loginDTO);

    RegisterVO userRegister(RegisterDTO  registerDTO);

    SetUserProfileVO setUserProfile(SetUserProfileDTO setUserProfileDTO);

    GetUserDataVO getUserData();

}
