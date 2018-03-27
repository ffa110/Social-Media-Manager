/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller   
public class LoginController 
{
    
	@Autowired 
	private UserRepository userRepository;

	@GetMapping(path="/login") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String username
                        , @RequestParam String password) 
        {

            List<User> user = userRepository.findByUserName(username);

            if(user.get(0).getUserName().equals(username) && user.get(0).getPassword().equals(password))
            {
                    return "<h3 style=\"color:green\">Loged In Successfully!</h3>";
            }
            
            return "<h3 style=\"color:red\">Login Failed</h3>";
		
            
	}

}