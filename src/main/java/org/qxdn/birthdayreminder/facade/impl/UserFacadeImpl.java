package org.qxdn.birthdayreminder.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.qxdn.birthdayreminder.context.PageTotalContextHolder;
import org.qxdn.birthdayreminder.facade.api.UserFacade;
import org.qxdn.birthdayreminder.model.dto.request.*;
import org.qxdn.birthdayreminder.model.dto.response.BaseResponse;
import org.qxdn.birthdayreminder.model.dto.response.vo.LoginVO;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserVO;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.enums.UserRoleEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.services.EmailService;
import org.qxdn.birthdayreminder.services.UserService;
import org.qxdn.birthdayreminder.services.converter.UserConverter;
import org.qxdn.birthdayreminder.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmailService emailService;

    @Override
    public BaseResponse<LoginVO> login(LoginRequest request) {
        User user = userService.getUserByUsername(request.getUsername());
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

    @Transactional
    @Override
    public BaseResponse<UserVO> register(RegisterRequest request) {
        User user = userService.getUserByUsername(request.getUsername());
        if (Objects.nonNull(user)){
            throw new BirthdayException(ErrorEnum.USER_EXIST);
        }
        // 默认权限
        if (StringUtils.isBlank(request.getRole())){
            request.setRole(UserRoleEnum.USER.getRole());
        }
        user = userConverter.registerRequest2Model(request);
        user = userService.save(user);
        return BaseResponse.success(userConverter.convert2VO(user));
    }

    @Transactional
    @Override
    public BaseResponse<UserVO> updateUser( UpdateUserRequest request) {
        UserRoleEnum role = UserRoleEnum.getByRole(request.getRole());
        User user = userService.getUserById(request.getUserId());
        user.setAvatar(request.getAvatar());
        user.setEmail(request.getEmail());
        user.setRole(role);
        user = userService.update(user);
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

    @Transactional
    @Override
    public BaseResponse<Void> resetPassword(ResetPasswordRequest request) {
        CheckUtils.notNull(request.getUserId(),request.getPassword(),request.getConfirmPassword());
        CheckUtils.checkEquals(request.getPassword(),request.getConfirmPassword());
        //TODO: 权限
        //UserSessionVO userSessionVO = UserSessionContext.get();
        //User user = userService.getUserById(request.getUserId());
        User resetUser = userService.getUserById(request.getUserId());
        resetUser.setPassword(PasswordUtils.encode(request.getPassword()));
        userService.update(resetUser);
        return new BaseResponse<>();
    }

    @Override
    public BaseResponse<Void> forgetPassword(ForgetPasswordRequest request) {
        User user = userService.getUserByEmail(request.getEmail());
        // TODO： 有待完成
        LogUtils.warn(log,"忘记密码功能未完成");
        return null;
    }
}
