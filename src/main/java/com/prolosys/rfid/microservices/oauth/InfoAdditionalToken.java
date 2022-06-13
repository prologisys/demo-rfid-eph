package com.prolosys.rfid.microservices.oauth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;
import com.prolosys.rfid.microservices.users.services.PermissionService;
import com.prolosys.rfid.microservices.users.services.UserService;

@Component
public class InfoAdditionalToken implements TokenEnhancer {

	@Value("${com.prolosys.rfid.root.username}")
	private String root;
	
    @Value("${app.prefix}")
    private String appPrefix;
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;

    private Logger LOGGER = LoggerFactory.getLogger(InfoAdditionalToken.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserEntity userEntity = userService.findByUsername(authentication.getName());
        
//        List<String> permissionStringList = new ArrayList<String>();
//  
//    	if (StringUtils.equals(authentication.getName(), root)) {
//			 Arrays.asList(PermissionEnum.values()).forEach(permission -> {
//	        	if((permission.name().toString().startsWith(appPrefix.toUpperCase()) || permission.name().toString().startsWith("PLS")) && !permission.name().contains("BACK")  && !permission.name().contains("FRONT")) {
//	        		permissionStringList.add(permission.name());
//	        	}
//			 });
//
//    	}else {
//    		List<PermissionEntity> permissionEntityList = permissionService.findAllByUserId(userEntity.getId());
//			for (PermissionEntity permissionEntity : permissionEntityList) {
//				permissionStringList.add(permissionEntity.getName().toString());
//			}
//    	}
//    	permissionStringList.add("APP_HOME");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("username", userEntity.getUsername());
        map.put("firstname", userEntity.getFirstname());
        map.put("lastname", userEntity.getLastname());
        map.put("email", userEntity.getEmail());
//        map.put("permissions", permissionStringList);

        authentication.setDetails(map);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);

        return accessToken;
    }

}
