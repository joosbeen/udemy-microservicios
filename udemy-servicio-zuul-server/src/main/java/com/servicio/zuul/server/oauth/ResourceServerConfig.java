package com.servicio.zuul.server.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${config.security.oauth.jwt.key}")
	private String signingKey;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/", "/api/items/", "/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/{id}", 
				"/api/items/{id}/{cantidad}", 
				"/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.cors()
		.configurationSource(corsConfigurationSource());
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
	
	/**
	 * Configurar Cors para spring security, para todas la rutas.
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		corsConfiguration.setAllowedOrigins(Arrays.asList("*")); //Cualquier origen
		
		//Los metodos permitos, OPTIONS lo utiliza oauth2.
		corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		//
		corsConfiguration.setAllowCredentials(true);
		//Authorization para los token
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
		
		url.registerCorsConfiguration("/**", corsConfiguration);
		
		return url;
	}
	
	/**
	 * Configuraion global para los cros, dar la prioridad.
	 * @return FilterRegistrationBean->CorsFilter
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
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
		token.setSigningKey(signingKey);
		return token;
	}

}
