package com.sanmaru.controllers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    final static Logger log = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String root () {
        log.info("======= info ");
        log.debug("======= debug");
        log.warn("===== warn");
        log.error("===== error");
        return "root1";
    }

}
