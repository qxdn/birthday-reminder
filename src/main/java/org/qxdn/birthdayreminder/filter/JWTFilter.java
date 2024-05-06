package org.qxdn.birthdayreminder.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.qxdn.birthdayreminder.context.UserSessionContext;
import org.qxdn.birthdayreminder.model.constants.BirthdayConstants;
import org.qxdn.birthdayreminder.model.dto.response.vo.UserSessionVO;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.qxdn.birthdayreminder.model.model.User;
import org.qxdn.birthdayreminder.services.UserService;
import org.qxdn.birthdayreminder.utils.JWTUtils;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    private final List<String> notFilterPath = List.of("/api/user/login", "/api/character/birthday","/v3/api-docs","/swagger-ui/*");
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(BirthdayConstants.JWT_HEADER);
        LogUtils.debug(log, "get JWT: {}", jwt);
        try {
            Long userId = JWTUtils.verifyToken(jwt);
            User user = userService.getUserById(userId);
            if (Objects.isNull(user)) {
                throw new BirthdayException(ErrorEnum.NOT_LOGIN);
            }
            // 设置登录用户
            UserSessionVO userSessionVO = new UserSessionVO();
            userSessionVO.setId(user.getId());
            // 设置context
            UserSessionContext.set(userSessionVO);
            // filter
            filterChain.doFilter(request, response);
        } catch (BirthdayException e) {
            // JWT错误
            LogUtils.error(log, "JWT认证失败" ,e);
            resolver.resolveException(request, response, null, e);
        } catch (Exception e) {
            // 未知错误
            LogUtils.error(log, "JWT认证失败" ,e);
            resolver.resolveException(request, response, null, new BirthdayException(ErrorEnum.FAIL,e));
        } finally {
            // 移除登录
            UserSessionContext.remove();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (!path.startsWith("/api")){
            return true;
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return notFilterPath.stream().anyMatch(p -> antPathMatcher.match(p, path));
    }
}
