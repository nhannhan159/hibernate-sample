package com.nhannhan159.sample.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  tien.tan
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping(value = "/weather")
    public String index() {
        log.info("Start weather app!");
        return "weather";
    }

}
