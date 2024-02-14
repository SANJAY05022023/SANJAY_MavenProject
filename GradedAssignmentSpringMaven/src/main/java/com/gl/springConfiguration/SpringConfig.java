package com.gl.springConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.gl.controller")
public class SpringConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SpringResourceTemplateResolver templateReslover() {
		SpringResourceTemplateResolver reslover = new SpringResourceTemplateResolver();
		reslover.setPrefix("/WEB-INF/pages/");
		reslover.setSuffix(".html");
		reslover.setTemplateMode(TemplateMode.HTML);
		reslover.setApplicationContext(applicationContext);
		return reslover;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateReslover());
		engine.setEnableSpringELCompiler(true);
		return engine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver vr = new ThymeleafViewResolver();
		vr.setTemplateEngine(templateEngine());
		return vr;
	}
}
