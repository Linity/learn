package cn.com.ljw.controller;

import cn.com.ljw.model.LogModel;
import cn.com.ljw.service.ChainLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Steph_Lin on 2020/3/18.
 */
@Slf4j
@Api(tags = "后端链上日志操作")
@RestController
@RequestMapping("/log")
public class ChainLogController {

    @Autowired
    private ChainLogService chainLogService;

    @ApiOperation("日志上链")
    @PostMapping("/saveChainLog")
    public int saveChainLog(@RequestBody LogModel logModel) {
        return chainLogService.save(logModel);
    }

}
