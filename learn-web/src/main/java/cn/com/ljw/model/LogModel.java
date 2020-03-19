package cn.com.ljw.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Steph_Lin on 2020/3/18.
 */
@Data
public class LogModel {
    /**
     * 日志id
     */
    private String code;

    /**
     * 操作ip
     */
    private String ip;

    /**
     * 操作时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date optTime;

    /**
     * 功能名称
     */
    private String moduleName;

    /**
     * 交互数据
     */
    private String content;

    /**
     * 日志操作类型
     */
    private String optType;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 所属机构
     */
    private String department;
}
