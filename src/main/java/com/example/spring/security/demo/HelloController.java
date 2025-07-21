package com.example.spring.security.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class HelloController {

    @GetMapping("/public")
    public String publicEndpoint(HttpServletRequest httpRequest) {
        return "This is a public endpoint "+httpRequest.getSession().getId();
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a secured endpoint.";
    }
}
