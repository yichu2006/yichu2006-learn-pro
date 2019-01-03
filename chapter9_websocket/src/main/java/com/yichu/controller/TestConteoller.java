package com.yichu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConteoller {

    @Autowired
    private WebSocket webSocket;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() throws Exception {
        webSocket.sendInfo("你好，群发！！！！");
        return "Hello World2";
    }
}
