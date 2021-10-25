package com.sanmaru.controllers;

import com.sanmaru.repositories.UserHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    final static Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @GetMapping("/")
    public String root () {

        //https://itstory.tk/entry/Spring-Security-%ED%98%84%EC%9E%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%95%9C-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%A0%95%EB%B3%B4-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return "username : " + userDetails.getUsername()
                + "<br/>Role : " + userDetails.getAuthorities()
                + "<br/><a href='/logout'>logout</a>";
    }
}
