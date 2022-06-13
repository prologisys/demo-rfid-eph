package com.prolosys.rfid.microservices.oauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prolosys.rfid.common.constants.PermissionEnum;
import com.prolosys.rfid.microservices.users.repositories.entities.PermissionEntity;
import com.prolosys.rfid.microservices.users.repositories.entities.UserEntity;
import com.prolosys.rfid.microservices.users.services.PermissionService;
import com.prolosys.rfid.microservices.users.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${com.prolosys.rfid.root.username}")
	private String rootUsername;
	
    @Value("${app.prefix}")
    private String appPrefix;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;

    private Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	UserEntity userEntity = userService.findByUsername(username);
    	
    	if(userEntity==null) {
    		LOGGER.error(String.format("No se encontró el usuario %s con las crendenciales ingresadas",username));
    		throw new UsernameNotFoundException(String.format("No se encontró el usuario %s con las crendenciales ingresadas",username));
    	}
    	
    	List<GrantedAuthority> grantedAuthorityList;
    	
    	if(username.equals(rootUsername)) {
    		grantedAuthorityList = new ArrayList<GrantedAuthority>();
    		 Arrays.asList(PermissionEnum.values()).forEach(permission -> {
	        	if((permission.name().toString().startsWith(appPrefix.toUpperCase())) && !permission.name().contains("BACK")  && !permission.name().contains("FRONT")) {
	        		grantedAuthorityList.add(new SimpleGrantedAuthority(permission.name()));
	        	}
    		 });
    	}else {
    		
    		List<PermissionEntity> permissionEntityList = permissionService.findAllByUserId(userEntity.getId());
    		
				grantedAuthorityList = permissionEntityList
				      .stream()
				      .map(permission -> new SimpleGrantedAuthority(permission.getName().toString()))
//		          .peek(authority -> LOGGER.info("Permiso de " + username + ": " + authority.getAuthority()))
				      .collect(Collectors.toList());
				
				grantedAuthorityList.add(new SimpleGrantedAuthority("SCL_APP_HOME"));
				
				
				
				
		        //Parámetros: username, password, enabled, accountNonExpired,credentialsNonExpired,accountNonLocked
		        return new User(
		        		userEntity.getUsername(),
		        		userEntity.getPassword(),
		        		userEntity.getEnabled(),
		            true,
		            true,
		            true,
		            grantedAuthorityList);
				
				
				
			
    	}
    	
    	return null;

    }

}
