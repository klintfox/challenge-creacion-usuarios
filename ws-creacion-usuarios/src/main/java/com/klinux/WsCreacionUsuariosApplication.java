package com.klinux;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.klinux.config.JWTAuthorizationFilter;

@SpringBootApplication
public class WsCreacionUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsCreacionUsuariosApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()					
					.antMatchers("/console/**").permitAll()
					.antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest()					
					.authenticated();

		}

		@Bean
		org.h2.tools.Server h2Server() {
		    Server server = new Server();
		    try {
		        server.runTool("-tcp");
		        server.runTool("-tcpAllowOthers");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return server;

		}
	}
}
