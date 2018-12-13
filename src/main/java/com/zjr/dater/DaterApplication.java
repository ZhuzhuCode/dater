package com.zjr.dater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableCaching//开启缓存
@EnableScheduling//开启定时任务
@SpringBootApplication
public class DaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaterApplication.class, args);
	}
}
