package com.example.SpringTrySample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 　簡単なログ出力の実装
 *
 *　　このコントローラーで実装されている/loggingsampleにアクセスした際に
 * 　 コンソールにinfoレベルでログを出力する
 */
@RestController
public class LoggingSampleController {

    Logger logger = LoggerFactory.getLogger(LoggingSampleController.class);

    @GetMapping("loggingsample")
    public String get(){
        logger.info("access GET /loggingsample");
        return "Hello";
    }
}
