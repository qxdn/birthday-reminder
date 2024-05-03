package org.qxdn.birthdayreminder.facade.api;

import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;

public interface UserFacade {

    /**
     * 登录
     * @param request 登录请求
     * @return 登录响应
     */
    BaseResponse<LoginVO> login(LoginRequest request);
}
