package net.backend.shopbackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.backend.shopbackend.dao.UserDAO;
import net.backend.shopbackend.dto.Address;
import net.backend.shopbackend.dto.Cart;
import net.backend.shopbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static UserDAO userDAO;
	
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.backend.shopbackend");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");	
	}
	
	@Test
	public void testAddUser(){
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
	
		cart = new Cart();
		
		// linked the address with the user
		address.setUserId(user.getId());
		
		// linked the cart with the user
		cart.setUser(user);
		
		// link the user with the cart
		user.setCart(cart);
	

		assertEquals("Failed to add the user!", true, userDAO.add(user));
	
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
	
	}
	
}
