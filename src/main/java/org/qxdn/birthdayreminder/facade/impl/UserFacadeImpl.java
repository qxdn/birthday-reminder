package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.facade.api.UserFacade;
import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.services.UserService;
import org.qxdn.birthdayreminder.services.converter.UserConverter;
import org.qxdn.birthdayreminder.utils.CheckUtils;
import org.qxdn.birthdayreminder.utils.JWTUtils;
import org.qxdn.birthdayreminder.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public BaseResponse<LoginVO> login(LoginRequest request) {
        CheckUtils.notBlank(request.getName(),request.getPassword());
        User user = userService.getUserByName(request.getName());
        if (Objects.isNull(user) || PasswordUtils.check(request.getPassword(),user.getPassword())){
            throw new BirthdayException(ErrorEnum.LOGIN_FAIL);
        }
        LoginVO loginVO = new LoginVO();
        UserVO userVO = UserConverter.convert2VO(user);
        String token = JWTUtils.generateToken(user.getId());
        loginVO.setUser(userVO);
        loginVO.setToken(token);
        return BaseResponse.success(loginVO);
    }
}
