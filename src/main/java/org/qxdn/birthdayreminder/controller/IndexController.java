package org.qxdn.birthdayreminder.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.qxdn.birthdayreminder.utils.LogUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class IndexController implements ErrorController {

    @RequestMapping("/")
    public byte[] index() throws IOException {
        Resource resource = new ClassPathResource("/static/index.html");
        InputStream inputStream = resource.getInputStream();
        byte[] content = inputStream.readAllBytes();
        inputStream.close();
        return content;
    }
    @RequestMapping("/**")
    public byte[] index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String path = request.getRequestURI();
            Resource resource = new ClassPathResource("/static" + path);
            InputStream inputStream = resource.getInputStream();
            byte[] content = inputStream.readAllBytes();
            inputStream.close();
            return content;
        } catch (IOException e) {
            LogUtils.error(log, "error:{}", e.getMessage());
            response.sendRedirect("/");
            return null;
        }
    }



}
