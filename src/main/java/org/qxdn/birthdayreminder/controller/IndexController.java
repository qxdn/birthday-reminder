package org.qxdn.birthdayreminder.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;


@RestController
public class IndexController {

    @RequestMapping("/")
    public byte[] index() throws IOException {
        Resource resource = new ClassPathResource("/static/index.html");
        InputStream inputStream = resource.getInputStream();
        byte[] content = inputStream.readAllBytes();
        inputStream.close();
        return content;
    }
    @RequestMapping("/**")
    public byte[] index(HttpServletRequest request) throws IOException {
        String path = request.getRequestURI();
        Resource resource = new ClassPathResource("/static" + path);
        InputStream inputStream = resource.getInputStream();
        byte[] content = inputStream.readAllBytes();
        inputStream.close();
        return content;
    }
}
