package com.wl.desafiounidac.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;;

@Configuration
public class SecurityConfig {
	
	// CORS - resolve problema de policiamento de rotas credenciadas no navegador
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
		List<String> all = Arrays.asList("*");
		
		CorsConfiguration corsConf = new CorsConfiguration();
		corsConf.setAllowedMethods(all);
		corsConf.setAllowedOriginPatterns(all);
		corsConf.setAllowedHeaders(all);
		corsConf.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConf);
		
		CorsFilter corsFilter = new CorsFilter(source);
		
		FilterRegistrationBean<CorsFilter> filter =	new FilterRegistrationBean<CorsFilter>(corsFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return filter;
	}
}
