package cn.com.ljw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author linjw
 */
@SpringBootApplication(scanBasePackages = {"cn.com.ljw"})
@MapperScan(basePackages="cn.com.ljw.dao")
@EnableScheduling
@EnableEurekaServer
public class StartApp {

    public static void main( String[] args ) {
        SpringApplication.run(StartApp.class);
    }

//    @EnableWebSecurity
//    public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//            super.configure(http);
//        }
//    }
}
