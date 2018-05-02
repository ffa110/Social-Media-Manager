/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class PostController 
{
    private LinkedIn linkedin;
    private Twitter twitter;
    private ConnectionRepository connectionRepository;
    
    public PostController(LinkedIn linkedin, Twitter twitter, ConnectionRepository connectionRepository)
    {
        this.linkedin = linkedin;
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping("/post")
    public String getPost()
    {
        return "post";
    }
    
}
