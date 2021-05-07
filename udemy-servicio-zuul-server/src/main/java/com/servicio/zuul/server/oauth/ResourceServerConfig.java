package com.servicio.zuul.server.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/", "/api/items/", "/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/{id}", 
				"/api/items/{id}/{cantidad}", 
				"/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		/*
		 * http.authorizeRequests() .antMatchers("/api/security/oauth/**").permitAll()
		 * .antMatchers(HttpMethod.GET, "/api/productos/", "/api/items/",
		 * "/api/usuarios/usuarios").permitAll() .antMatchers(HttpMethod.GET,
		 * "/api/productos/{id}", "/api/items/{id}/{cantidad}",
		 * "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
		 * .antMatchers("/api/productos/**", "/api/items/**",
		 * "/api/usuarios/**").hasRole("ADMIN") //.antMatchers(HttpMethod.POST,
		 * "/api/productos/", "/api/items/", "/api/usuarios/usuarios/").hasRole("ADMIN")
		 * .anyRequest() .authenticated();
		 */
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.tokenStore(tokenStore());
		
	}

	@Bean
	public JwtTokenStore tokenStore() {
		
		return new JwtTokenStore(accessTokenConverter());
		
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {

		JwtAccessTokenConverter token = new JwtAccessTokenConverter();
		token.setSigningKey("algun_codigo_secreto_aeiou");
		return token;
	}

}
