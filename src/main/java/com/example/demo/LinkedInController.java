/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInNetworkUpdate;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Post;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class LinkedInController 
{
    private LinkedIn linkedin;
    
    private ConnectionRepository connectionRepository;
    
    public LinkedInController(LinkedIn linkedin, ConnectionRepository connectionRepository)
    {
        this.linkedin = linkedin;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping("/linkdin")
    public String helloLinkedin(Model model)
    {
        if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) 
        {
            return "redirect:/connect/linkedin";
        }
        
        LinkedInProfileFull profile = linkedin.profileOperations().getUserProfileFull();
         List<LinkedInNetworkUpdate> test = linkedin.networkUpdateOperations().getNetworkUpdates();
//        
  //      List<Post> linkedinPosts = linkedin.groupOperations().getPosts(Integer.BYTES).getPosts();
        
//        model.addAttribute("update", networkUpdates);

        ProfileOperations user = linkedin.profileOperations();
        System.out.println("Linkedin User ====> "+user.getUserProfile().getFirstName() + " " + user.getUserProfile().getLastName());
        model.addAttribute("linkedinProfile", linkedin.profileOperations().getUserProfileFull());
        
        return "linkedin";
    }
    
}
