package com.xtremedreamers.webuy.persistence;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Configuration
public class FaviconConfiguration {

	 @Bean
	 public SimpleUrlHandlerMapping faviconHandlerMapping() {
	  SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
	  mapping.setOrder(Integer.MIN_VALUE);
	  mapping.setUrlMap(Collections.singletonMap("/static/img/favicon-32x32.png",
	   faviconRequestHandler()));
	  return mapping;
	 }
	 
	 @Bean
	    protected ResourceHttpRequestHandler faviconRequestHandler() {
	        ResourceHttpRequestHandler requestHandler
	                = new ResourceHttpRequestHandler();
	        requestHandler.setLocations(Collections.singletonList(new ClassPathResource("/")));
	        return requestHandler;
	    }
	 
}