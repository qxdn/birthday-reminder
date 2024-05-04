package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;

public interface UserFacade {

    /**
     * 登录
     * @param request 登录请求
     * @return 登录响应
     */
    BaseResponse<LoginVO> login(LoginRequest request);


    /**
     * 注册
     * @param request 注册请求
     * @return 注册响应
     */
    BaseResponse<UserVO> register(RegisterRequest request);
}
