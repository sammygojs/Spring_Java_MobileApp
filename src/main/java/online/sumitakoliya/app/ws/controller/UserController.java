package online.sumitakoliya.app.ws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import online.sumitakoliya.app.ws.model.request.UpdateUserDetailsRequestModel;
import online.sumitakoliya.app.ws.model.request.UserDetailsRequestModel;
import online.sumitakoliya.app.ws.model.response.UserRest;
import online.sumitakoliya.app.ws.userservice.UserService;
import online.sumitakoliya.app.ws.userservice.impl.UserServiceImpl;
import online.sumitakoliya.app.ws.exceptions.UserServiceException;

@RestController
@RequestMapping("/users")	//http://localhost:8080/users
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1")int page,
			@RequestParam(value="limit", defaultValue="50")int limit,
			@RequestParam(value="sort", defaultValue="desc", required=false)String sort) {
		
		if(sort==null) sort="desc";
		return "get users was called with page "+page+" limit "+limit+" sort: "+sort; 
	}
	
	@GetMapping(path="/{userId}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public 
//	UserRest 
	ResponseEntity<UserRest>
//	String
	getUser(@PathVariable String userId) {
		
//		String fname = null;
//		int len = fname.length(); 
		
		if(true) throw new UserServiceException("A user service exception is thrown");
		
//		UserRest returnValue = new UserRest();
//		returnValue.setFirstName("a");
//		returnValue.setLastName("b");
//		returnValue.setEmail("a@g.com");
		
//		return "get user was called "+userId;
//		return returnValue;
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}else {
		return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
//		return "create user was called";
		
//		Before DI
//		UserRest returnValue = new UserServiceImpl().createUser(userDetails); 
		
//		Using @Autowired
		
		UserRest returnValue = userService.createUser(userDetails);
//		users.put(returnValue.getUserId(), returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails ) {
//		return "update user was called";
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
		
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//		return "delete user was called";
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}
