package com.prolosys.rfid.microservices.oauth;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.prolosys.rfid.common.constants.PermissionEnum;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;
    
    @Value("${api.prefix}")
    private String apiPrefix;
    
    @Value("${app.prefix}")
    private String appPrefix;
    
    @Autowired
    JwtTokenStore jwtTokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable().and().csrf().disable();
        
        Arrays.asList(PermissionEnum.values()).forEach(permission -> {
        	
        	if((permission.name().toString().startsWith(appPrefix.toUpperCase()) || permission.name().toString().startsWith("PLS")) && !permission.name().contains("BACK")  && !permission.name().contains("FRONT")) {
        		try {
        			http.authorizeRequests().antMatchers(HttpMethod.valueOf(permission.method().toUpperCase()), apiPrefix+permission.path()).hasAnyAuthority(permission.name().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	
        	if((permission.name().toString().startsWith(appPrefix) ||  permission.name().toString().startsWith("PLS"))  && (permission.name().contains("BACK")  ||  permission.name().contains("FRONT"))) {
        		try {
        			http.authorizeRequests().antMatchers(HttpMethod.valueOf(permission.method().toUpperCase()), apiPrefix+permission.path()).permitAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        
        });
        
        
        
        http.authorizeRequests()

//        		.antMatchers(HttpMethod.GET, ).permitAll()
//        		.antMatchers(HttpMethod.GET, ).permitAll()
//        		.antMatchers(HttpMethod.POST, "/rfid/api/v1/oauth/**").permitAll()
//        		.antMatchers(HttpMethod.OPTIONS, "/rfid/api/v1/oauth/**").permitAll()
//        		.antMatchers(HttpMethod.POST, "/oauth/**").permitAll()
//        		.antMatchers(HttpMethod.OPTIONS, "/oauth/**").permitAll()
        		
		.antMatchers(
				"/actuator/**",
				"/dev/**",
				
				
				"/", 
				"/libs/**", 
				"/css/**", 
				"/js/**", 
				"/fonts/**", 
				"/tpl/**", 
				"/l10n/**", 
				"/img/**",
				"/favicon.png",
				
				"/v2/api-docs**",
				"/swagger-ui.**",
				"/swagger-resources/**",
				"/webjars/**",
				
				
				"/rfid/api/v1/oauth/**",
				
				"/rfid/api/v1/pdf/**"
				
				).permitAll()
        		
        		
        .anyRequest().authenticated()
        .and()
        	.cors().configurationSource(corsConfigurationSource());
    	
    	
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "DELETE", "PUT", "GET", "OPTIONS"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));

        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

}
