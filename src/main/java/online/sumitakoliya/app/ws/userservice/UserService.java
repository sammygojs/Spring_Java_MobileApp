package online.sumitakoliya.app.ws.userservice;

import online.sumitakoliya.app.ws.model.request.UserDetailsRequestModel;
import online.sumitakoliya.app.ws.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
