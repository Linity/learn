package cn.com.ljw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(scanBasePackages = {"cn.com.ljw"})
@SpringBootApplication(scanBasePackages = {"cn.com.ljw"})
@MapperScan(basePackages="cn.com.ljw.dao")
@EnableScheduling
public class StartApp {

    public static void main( String[] args ) {
        SpringApplication.run(StartApp.class);
    }

}
