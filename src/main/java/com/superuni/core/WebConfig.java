package com.superuni.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.superuni.web"})
@PropertySource({"core.properties"})
public class WebConfig {
	
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//	
//	@Bean
//	public ServletRegistrationBean<DispatcherServlet>  getServletRegistrationBean () {
//		ServletRegistrationBean<DispatcherServlet> bean = 
//					new ServletRegistrationBean<DispatcherServlet>(new DispatcherServlet(),
//							"*.mvc", "*.do");
//		logger.info("servlet mapping complete");
//		return bean;
//	}
}
