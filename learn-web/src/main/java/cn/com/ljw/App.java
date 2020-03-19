package cn.com.ljw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="cn.com.ljw.dao")
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class);
    }

}
