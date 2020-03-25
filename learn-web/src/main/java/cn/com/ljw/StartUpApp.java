package cn.com.ljw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.com.ljw"})
@MapperScan(basePackages="cn.com.ljw.dao")
public class StartUpApp {

    public static void main( String[] args ) {
        SpringApplication.run(StartUpApp.class);
    }

}
