package com.party;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages="com.party.*.mapper")
@EnableTransactionManagement(proxyTargetClass=true)
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
public class HappyPartyBackstageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyPartyBackstageApplication.class, args);
	}
	
}
