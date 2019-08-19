package com.owner.reconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.owner.reconnect.loginout.ActiveUserStore;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class ReconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconnectApplication.class, args);
	}

	@Bean
	public ActiveUserStore activeUserStore() {
		return new ActiveUserStore();
	}
}
