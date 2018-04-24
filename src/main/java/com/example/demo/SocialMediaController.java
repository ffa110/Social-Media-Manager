/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class SocialMediaController 
{
    
    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public SocialMediaController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping("/twitter")
    public String helloTwitter(Model model) {
        
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        System.out.println(twitter.userOperations().getUserProfile().getProfileImageUrl());
        model.addAttribute(twitter.userOperations().getUserProfile());
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        
        
        
        model.addAttribute("tweets", tweets);
        
        return "helloTwitter";
    }
    
}
