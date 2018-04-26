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
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class FacebookController 
{
    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    
    @Inject
    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository)
    {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping("facebook")
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        FacebookProfile profile = facebook.userOperations().getUserProfile();
        System.out.println("Facebook last name =====> " + profile.getLastName());
        model.addAttribute("facebookProfile", profile);
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }
    
}
