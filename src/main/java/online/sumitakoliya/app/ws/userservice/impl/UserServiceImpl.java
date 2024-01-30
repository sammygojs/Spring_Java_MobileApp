package online.sumitakoliya.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.sumitakoliya.app.ws.model.request.UserDetailsRequestModel;
import online.sumitakoliya.app.ws.model.response.UserRest;
import online.sumitakoliya.app.ws.shared.Utils;
import online.sumitakoliya.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService{

	Map<String, UserRest> users;
	Utils utils;
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils=utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());
		

//		String userId = UUID.randomUUID().toString();
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
		if(users==null) users=new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
		
//		return null;
	}

}
