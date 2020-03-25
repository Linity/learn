package cn.com.ljw.service;

import cn.com.ljw.contract.service.ChainService;
import cn.com.ljw.dao.ChainLogMapper;
import cn.com.ljw.entity.ChainLog;
import cn.com.ljw.model.LogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Steph_Lin on 2020/3/18.
 */
@Service
public class ChainLogService {

    private static final Logger logger = LoggerFactory.getLogger(ChainLogService.class);

    @Autowired
    ChainLogMapper chainLogMapper;
    @Autowired
    ChainService chainService;

    public int save(LogModel logModel){
//        ChainLog chainLog = new ChainLog();
//        Date now = new Date();
////        supChainLog.setAppId(clientId);
//        chainLog.setCode(logModel.getCode());
//        chainLog.setContent(logModel.getContent());
//        chainLog.setDepartment(logModel.getDepartment());
//        chainLog.setOptTime(logModel.getOptTime());
//        chainLog.setState(false);
//        chainLog.setIp(logModel.getIp());
//        chainLog.setModuleName(logModel.getModuleName());
//        chainLog.setUserId(logModel.getUserId());
//        chainLog.setUserName(logModel.getUserName());
//        chainLog.setOptType("");
//        chainLog.setCreateTime(now);
//        chainLog.setUpdateTime(now);
        chainService.deployContract();
        logger.info("success");

//        return chainLogMapper.save(chainLog);
        return 1;
    }

}
