/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller   

public class RegistrationController 
{
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
    
    @RequestMapping(path="/signup") // This means URL's start with /demo (after Application path)
    public String getSignup()
    {
        return "signup";
    }
    
	@Autowired 
	private UserRepository userRepository;

	@PostMapping(path="/signup") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String email
                        , @RequestParam String username
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
                        String hashedPassword = passwordEncoder.encode(password);
                        newUser.setPassword(hashedPassword);
                        
                        userRepository.save(newUser);
                        return "<html><h1 style=\"color: green;\">Successful Registration</h1><meta http-equiv=\"refresh\" content=\"2; url=http://localhost:8080/login\" /></html>";   
                    }
                }
            }
            
		return "<html><h1>Failed</h1><meta http-equiv=\"refresh\" content=\"2; url=http://localhost:8080/signup\" /></html>";
            
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}