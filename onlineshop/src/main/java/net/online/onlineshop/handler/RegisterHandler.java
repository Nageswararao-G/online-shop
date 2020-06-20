package net.online.onlineshop.handler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.backend.shopbackend.dao.UserDAO;
import net.backend.shopbackend.dto.Address;
import net.backend.shopbackend.dto.Cart;
import net.backend.shopbackend.dto.User;
import net.online.onlineshop.model.RegisterModel;

@Component
public class RegisterHandler {

	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDAO userDao;
	
	public RegisterModel init() {
	return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel){
		String transitionValue = "success";
		User user = registerModel.getUser();
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		  user.setPassword(passwordEncoder.encode(user.getPassword()));

		userDao.add(user);
		
		Address billing = registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		userDao.addAddress(billing);
		return transitionValue;
	}
	
	public String validateUser(User user,MessageContext error){
		String transitionValue = "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword())) ){
			error.addMessage(new MessageBuilder()
			   .error().source("confirmPassword")
			   .defaultText("Password match the confirm password")
			   .build());
			
			transitionValue = "failure";
		}
		
		if(userDao.getByEmail(user.getEmail()) != null ){
			error.addMessage(new MessageBuilder()
			   .error().source("email")
			   .defaultText("Email address is already used")
			   .build());
			
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
}
