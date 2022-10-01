package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.userDTO.LoginDTO;
import com.example.BackendVolatile.dto.userDTO.RegisterDTO;
import com.example.BackendVolatile.dto.userDTO.SetUserProfileDTO;
import com.example.BackendVolatile.vo.stakeholder.EmployerStateVO;
import com.example.BackendVolatile.vo.userVO.*;

import java.util.List;

public interface UserService {

    LoginVO userLogin(LoginDTO loginDTO);

    RegisterVO userRegister(RegisterDTO  registerDTO);

    SetUserProfileVO setUserProfile(SetUserProfileDTO setUserProfileDTO);

    GetUserDataVO getUserData();

    List<EmployerStateVO> test();

}
