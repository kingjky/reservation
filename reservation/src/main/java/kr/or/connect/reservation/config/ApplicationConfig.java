package kr.or.connect.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.or.connect.reservation.config.DBConfig;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.reservation.dao", "kr.or.connect.reservation.service"})
@Import({DBConfig.class})
public class ApplicationConfig {
}
