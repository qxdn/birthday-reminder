package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryUserRequest;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;

import java.util.List;

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

    BaseResponse<UserVO> queryUserById(Long id);

    /**
     * 查询用户列表
     * @param request 查询请求
     * @return 用户列表
     */
    BaseResponse<List<UserVO>> queryUserList(QueryUserRequest request);
}
