package com.pica.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import pica.notification.config.NotificationConfig;

@Configuration
public class ConfigurationClass extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
	    if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
	     // LOGGER.debug("Sending Header....");
	      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	      // response.addHeader("Access-Control-Allow-Headers", "Authorization");
	      response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	      response.addHeader("Access-Control-Max-Age", "1");
	    }
	    filterChain.doFilter(request, response);
		
	}
	
	@Bean
	public NotificationConfig getNotificationConfig() {
		return new NotificationConfig();
	}

//	@Bean
//	public MongoClient mongo() {
//		return new MongoClient("localhost");
//	}
//
//	@Bean
//	public MongoTemplate mongoTemplate() throws Exception {
//		return new MongoTemplate(mongo(), "db");
//	}
}
