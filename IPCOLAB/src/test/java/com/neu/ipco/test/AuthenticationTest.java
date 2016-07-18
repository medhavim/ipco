/**
 * 
 */
package com.neu.ipco.test;

import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.AuthenticationService;

/**
 * @author Harsha
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/dispatcher-servlet.xml"})
public class AuthenticationTest {
	
	Logger LOGGER = Logger.getLogger(AuthenticationTest.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	@Test
	public void testCheckEmail(){
		
	}
	
	@Test
	public void testCheckUsername(){
		
	}
	
//	@Test
	public void testUserRegister(){
		
		User user = new User();
		
		UserType userType = new UserType();
		userType.setUserTypeDesc("user");
		try {
			userType = applicationUtilService.getUserType(userType);
		} catch (ApplicationUtilException e1) {
			e1.printStackTrace();
		}
		
		Credential credential = new Credential();
		credential.setUsername("peter");
		credential.setPassword("parker");
		
		user.setFirstName("Peter");
		user.setLastName("Parker");
		user.setEmail("peter@parker.set");
		user.setUserType(userType);
		user.setCredential(credential);
		
		
		try {
			user = authenticationService.userRegister(user);
			assertNotEquals(Integer.valueOf(0), user.getUserId());
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidUser(){
		
	}
	
	@Test
	public void testResetCredentials(){
		
	}
	
	@Test
	public void testAddAdmin(){
		
	}
	
	@Test
	public void testAdminLogin(){
		
	}
	
	@Test
	public void createUserTypes(){
		LOGGER.debug("ApplicationUtilPrograms: main: Start");
		
		UserType user = new UserType();
		user.setUserTypeDesc("user");
		
		UserType admin = new UserType();
		admin.setUserTypeDesc("admin");
		
		try {
			applicationUtilService.addUserType(user);
		} catch (ApplicationUtilException e1) {
			LOGGER.debug("Exception: Problem with adding user type user, "+ e1.getMessage());
//			e1.printStackTrace();
		}
		try {
			applicationUtilService.addUserType(admin);
		} catch (ApplicationUtilException e) {
			LOGGER.debug("Exception: Problem with adding user type admin, "+ e.getMessage());
//			e.printStackTrace();
		}	
	}
}
