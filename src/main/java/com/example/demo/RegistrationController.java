/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller   
@RequestMapping(path="/registration1") // This means URL's start with /demo (after Application path)
public class RegistrationController 
{
    
	@Autowired 
	private UserRepository userRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String username
			, @RequestParam String email
                        , @RequestParam String password) 
        {

            if(username.length() > 0)
            {
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            
                if(newUser.isValid())
                {
                    if(userRepository.findByUserName(username).isEmpty() && userRepository.findByEmail(email).isEmpty())
                    {
                        userRepository.save(newUser);
                        return "Username: " + newUser.getUserName() + "<br>Email: " + newUser.getEmail() + 
                               "<br><h3 style=\"color:green\">Registered Successfully</h3";   
                    }
                }
            }
            
		return "Registeration Failed";
            
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}