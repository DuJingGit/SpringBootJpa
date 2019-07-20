
package com.itmayiedu.app;

import com.itmayiedu.config.DBConfig1;
import com.itmayiedu.config.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ComponentScan(basePackages = { "com.itmayiedu" })
//@EnableJpaRepositories(basePackages = "com.itmayiedu.dao.second")
@EnableAutoConfiguration
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })
//@EntityScan(basePackages = "com.itmayiedu.entity.second")
// @SpringBootApplication
//@EnableCaching
//@EnableScheduling
public class App extends SpringBootServletInitializer {
	//重写configure 从而使Tomcat识别启动类
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
