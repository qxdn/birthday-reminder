package org.qxdn.birthdayreminder.controller;

import org.qxdn.birthdayreminder.facade.api.UserFacade;
import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    /**
     * 登录
     * @param request 登录请求
     * @return 登录响应
     */
    @PostMapping("/login")
    public BaseResponse<LoginVO> login(@RequestBody LoginRequest request) {
        return userFacade.login(request);
    }


    /**
     * 注册
     * @param request 注册请求
     * @return 注册响应
     */
    @PostMapping("/register")
    public BaseResponse<UserVO> register(@RequestBody RegisterRequest request) {
        return userFacade.register(request);
    }
}
