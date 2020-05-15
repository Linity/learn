package cn.com.ljw.controller;

import cn.com.ljw.websocket.WebSocket;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Steph_Lin on 2020/5/9.
 */
@Slf4j
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    WebSocket webSocket;

    @GetMapping("/websocket")
    public String websocket(){

        webSocket.sendTextMessage("1", "处理完毕！");

        return "success";
    }

}
