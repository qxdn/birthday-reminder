package org.qxdn.birthdayreminder.facade.impl;

import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.facade.api.UserFacade;
import org.qxdn.birthdayreminder.model.dto.request.LoginRequest;
import org.qxdn.birthdayreminder.model.dto.request.QueryUserRequest;
import org.qxdn.birthdayreminder.model.dto.request.RegisterRequest;
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
import org.qxdn.birthdayreminder.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Override
    public BaseResponse<LoginVO> login(LoginRequest request) {
        CheckUtils.notBlank(request.getName(),request.getPassword());
        User user = userService.getUserByName(request.getName());
        if (Objects.isNull(user) || !PasswordUtils.check(request.getPassword(),user.getPassword())){
            throw new BirthdayException(ErrorEnum.LOGIN_FAIL);
        }
        LoginVO loginVO = new LoginVO();
        UserVO userVO = userConverter.convert2VO(user);
        String token = JWTUtils.generateToken(user.getId());
        loginVO.setUser(userVO);
        loginVO.setToken(token);
        return BaseResponse.success(loginVO);
    }

    @Override
    public BaseResponse<UserVO> register(RegisterRequest request) {
        CheckUtils.notBlank(request.getName(),request.getPassword());
        User user = userService.getUserByName(request.getName());
        if (Objects.nonNull(user)){
            throw new BirthdayException(ErrorEnum.USER_EXIST);
        }
        user = userConverter.registerRequest2Model(request);
        user = userService.save(user);
        return BaseResponse.success(userConverter.convert2VO(user));
    }

    @Override
    public BaseResponse<UserVO> queryUserById(Long id) {
        CheckUtils.notNull(id);
        User user = userService.getUserById(id);
        return BaseResponse.success(userConverter.convert2VO(user));
    }

    @Override
    public BaseResponse<List<UserVO>> queryUserList(QueryUserRequest request) {
        List<User> users = userService.queryUsers(request);
        List<UserVO> userVOS = StreamUtils.map(users,userConverter::convert2VO);
        Long total = PageTotalContextHolder.get();
        PageTotalContextHolder.remove();
        return new BaseResponse<>(userVOS,total);
    }
}
