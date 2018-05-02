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
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class SocialMediaController 
{
    
    private Twitter twitter;
    private LinkedIn linkedin;
    private ConnectionRepository connectionRepository;

    @Inject
    public SocialMediaController(Twitter twitter, LinkedIn linkedin, ConnectionRepository connectionRepository) {
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
         
       return "timelines";
    }
     
    @RequestMapping("/timelines")
    public String getTimelines(ModelMap model) {
        
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/mediaConnect";
        }
        if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) 
        {
            return "redirect:/mediaConnect";
        }
        
        ProfileOperations profileOperations = linkedin.profileOperations();
        LinkedInProfile user = profileOperations.getUserProfile();
        System.out.println("Linkedin User ====> "+user.getFirstName() + " " + user.getLastName());
        model.addAttribute("linkedinProfile", user);
        
        System.out.println(twitter.userOperations().getUserProfile().getProfileImageUrl());
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();

        model.addAttribute("tweets", tweets);
        
        return "timelines";
    }
    
    @RequestMapping("/linkedin")
    public String helloLinkedin(Model model)
    {
        if(connectionRepository.findPrimaryConnection(LinkedIn.class) == null) 
        {
            return "redirect:/connect/linkedin";
        }
        
        // Need permission to access more data
    //      List<Post> linkedinPosts = linkedin.groupOperations().getPosts(Integer.BYTES).getPosts();
    //        model.addAttribute("update", networkUpdates);

        ProfileOperations profileOperations = linkedin.profileOperations();
        LinkedInProfile user = profileOperations.getUserProfile();
        System.out.println("Linkedin User ====> "+user.getFirstName() + " " + user.getLastName());
        model.addAttribute("linkedinProfile", user);

        return "timelines";
    }
    
    @GetMapping("/post")
    public String getPost()
    {
        
        return "post";
    }
    
    @PostMapping("/post")
    public String sendPost(@RequestParam String post, 
                           @RequestParam String checkTwitter,
                           @RequestParam String checkFacebook,
                           @RequestParam String checkLinkedin
                           )
    {
        if(!(post.equals("")))
        {
            if(checkTwitter.equals("on"))
            {
                twitter.timelineOperations().updateStatus(post);
//                System.out.println("Twitter ====> posted");
            }
            if(checkFacebook.equals("on"))
            {
                /**
                 * Needs more permissions to post from Facebook
                 * Facebook does not allow developers to post from a linkedin app
                 */
                System.out.println("Facebook ====> posted");
            }
            if(checkLinkedin.equals("on"))
            {
                /**
                 * Needs more permissions to post from LinkedIn
                 * LinkedIn does not allow developers to post from a linkedin app
                 */
//                linkedin.groupOperations().createPost(Integer.BYTES, post, post);
                System.out.println("LinkedIn ====> posted");
            }
        }
        return "post";
    }
    
    
    
    
    
}
