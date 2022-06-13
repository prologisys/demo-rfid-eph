package com.prolosys.rfid.microservices.oauth;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizacionServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;

	@Value("${config.security.oauth.webClient.id}")
	private String webId;

	@Value("${config.security.oauth.webClient.secret}")
	private String webSecret;
		
	@Value("${config.security.oauth.movilClient.id}")
	private String movilId;

	@Value("${config.security.oauth.movilClient.secret}")
	private String movilSecret;
		
	@Value("${api.prefix}")
	private String apiPrefix;

	@Autowired
	private InfoAdditionalToken infoAdditionalToken;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenStore jwtTokenStore;

	@Autowired
	JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	CorsConfigurationSource corsConfigurationSource;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//		JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
//
//		List<ClientDetails> clientDetailsList = jdbcClientDetailsService.listClientDetails();
//
//		for (ClientDetails clientDetails : clientDetailsList) {
//			String id = clientDetails.getClientId();
//			jdbcClientDetailsService.removeClientDetails(id);
//		}
//
//		clients.jdbc(dataSource).withClient(clientId).secret(bCryptPasswordEncoder.encode(clientSecret))
//				.scopes("read", "write").autoApprove(true).authorizedGrantTypes("password", "refresh_token")
//				.accessTokenValiditySeconds(4 * 3600) // 4 horas
//				.refreshTokenValiditySeconds(6 * 3600) // 6horas
//
//				.and().withClient("svca-service").secret("password")
//				.authorizedGrantTypes("client_credentials", "refresh_token").scopes("server").autoApprove(true)
//				.accessTokenValiditySeconds(4 * 3600) // 4 horas
//				.refreshTokenValiditySeconds(6 * 3600) // 6horas
//
//				.and().build();

		
		
		
		
		
		clients
		.inMemory()
			.withClient(webId)
			.secret(bCryptPasswordEncoder.encode(webSecret))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")
//			.accessTokenValiditySeconds(4*3600) //4 horas
//			.refreshTokenValiditySeconds(6*3600) //6horas
			
			.accessTokenValiditySeconds((60*60)*4) //4 horass
			.refreshTokenValiditySeconds((60*60)*6) //6horas
		.and()
			.withClient(movilId)
			.secret(bCryptPasswordEncoder.encode(movilSecret))
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds((60*200)) //4 horas
			.refreshTokenValiditySeconds((60*400)); //6horas
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		

//	    Map<String, CorsConfiguration> corsConfigMap = new HashMap<>();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    //TODO: Make configurable
//	    config.setAllowedOrigins(Collections.singletonList("*"));
//	    config.setAllowedMethods(Collections.singletonList("*"));
//	    config.setAllowedHeaders(Collections.singletonList("*"));
//	    corsConfigMap.put(this.apiPrefix+"/oauth/token", config);
//	    endpoints.getFrameworkEndpointHandlerMapping().setCorsConfigurations(corsConfigMap);
		
		
		
		
//		endpoints.getFrameworkEndpointHandlerMapping().setCorsConfigurationSource(corsConfigurationSource);
		 

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, infoAdditionalToken));

//		endpoints.prefix(this.apiPrefix)
		endpoints.pathMapping("/oauth/token", apiPrefix+"/oauth/token")
		.authenticationManager(authenticationManager).tokenStore(jwtTokenStore).reuseRefreshTokens(false) 
		// Permite
	// regenerar
	// nuevos
	// token
	// a
	// partir
	// de
	// un
	// refresh_token
				.accessTokenConverter(jwtAccessTokenConverter);

		endpoints.tokenStore(jwtTokenStore).tokenEnhancer(tokenEnhancerChain);
		
		
		
	   
		
		
	}

}