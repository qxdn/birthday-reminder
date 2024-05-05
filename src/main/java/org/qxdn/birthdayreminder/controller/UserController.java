package org.qxdn.birthdayreminder.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.qxdn.birthdayreminder.context.UserSessionContext;
import org.qxdn.birthdayreminder.facade.api.UserFacade;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryUserRequest;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserSessionVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/user")
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
        BaseResponse<LoginVO> response = userFacade.login(request);
        HttpServletResponse servletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        servletResponse.addHeader(BirthdayConstants.JWT_HEADER, response.getData().getToken());
        return response;
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


    @GetMapping("/current")
    public BaseResponse<UserVO> queryCurrentUser() {
        UserSessionVO userSessionVO = UserSessionContext.get();
        return userFacade.queryUserById(userSessionVO.getId());
    }

    @GetMapping("/query")
    public BaseResponse<List<UserVO>> queryUserList(QueryUserRequest request){
        return userFacade.queryUserList(request);
    }

    @RequestMapping("/logout")
    public BaseResponse<Void> logout() {
        UserSessionContext.remove();
        return new BaseResponse<>();
    }
}
