package com.prolosys.rfid.microservices.users.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class RegisterBeans {
	
    @Value("${config.security.oauth.jwt.key}")
    private String jwtKey;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtKey);
        return tokenConverter;
    }

//	@Bean
//	public ObjectMapper objectMapper() {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		mapper.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
//		return mapper;
//	}

//	@Bean
//	public TaskExecutor threadPoolTaskExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(4);
//		executor.setMaxPoolSize(4);
//		executor.setThreadNamePrefix("default_task_executor_thread");
//		executor.initialize();
//		return executor;
//	}

}