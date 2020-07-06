package com.myclass.validation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
@Component
public class UserValidation implements org.springframework.validation.Validator {
	
	@Autowired
	UserRepository userRepository;
	public boolean supports(Class<?> clazz) {
		// kiểm tra xem lớp đối tượng validate có phải là User hay không
		return User.class.equals(clazz);
	}
	public void validate(Object target, Errors errors) {
		//Ép đối tượng truyền vào thành User
		User user = (User)target;
	
		if(user.getPassword()==null || user.getPassword().length()==0) {
			errors.rejectValue("password", "user", " Vui lòng nhập mật khẩu!");
		}
		if(!user.getPassword().equals(user.getConfirmpassword())){
			errors.rejectValue("confirmpassword", "user", "Mật khẩu không khớp!");
		}
		else if(user.getConfirmpassword()==null || user.getConfirmpassword().length()==0) {
			errors.rejectValue("confirmpassword", "user", " Vui lòng nhập mật khẩu!");
		}
		
	}
}
