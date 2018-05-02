/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import javax.inject.Inject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInNetworkUpdate;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.NetworkUpdateOperations;
import org.springframework.social.linkedin.api.Post;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class TwitterController 
{
    
    private Twitter twitter;
    private LinkedIn linkedin;
    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter, LinkedIn linkedin, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping("/twitter")
    public String helloTwitter(ModelMap model) 
    {
         if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
         
        System.out.println(twitter.userOperations().getUserProfile().getProfileImageUrl());
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
 
        model.addAttribute("tweets", tweets);
         
       return "helloTwitter";
    }
     
    @RequestMapping("/timelines")
    public String getTimelines(ModelMap model) {
        
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
        if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) 
        {
            return "redirect:/connect/linkedin";
        }
        
        ProfileOperations profileOperations = linkedin.profileOperations();
        LinkedInProfile user = profileOperations.getUserProfile();
        System.out.println("Linkedin User ====> "+user.getFirstName() + " " + user.getLastName());
        model.addAttribute("linkedinProfile", user);
        
        System.out.println(twitter.userOperations().getUserProfile().getProfileImageUrl());
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        
        
        
        model.addAttribute("tweets", tweets);
        
        return "linkedin";
    }
    
    @RequestMapping("/linkedin")
    public String helloLinkedin(Model model)
    {
        if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) 
        {
            return "redirect:/connect/linkedin";
        }
        
    //        
    //      List<Post> linkedinPosts = linkedin.groupOperations().getPosts(Integer.BYTES).getPosts();

    //        model.addAttribute("update", networkUpdates);

        ProfileOperations profileOperations = linkedin.profileOperations();
        LinkedInProfile user = profileOperations.getUserProfile();
        System.out.println("Linkedin User ====> "+user.getFirstName() + " " + user.getLastName());
        model.addAttribute("linkedinProfile", user);

        return "linkedin";
    }
    
    
}
