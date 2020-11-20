package cn.com.ljw.controller;

//import cn.com.ljw.kafka.KafkaProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steph_Lin
 * @date 2020/8/27
 */
@Slf4j
@Api(tags = "kafka操作")
@RestController
@RequestMapping("/kafka操作")
public class KafkaController {

//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//    @ApiOperation("根据区块哈希获取区块信息")
//    @GetMapping("/send")
//    public void send() {
//        kafkaProducer.send("test");
//    }

}
