/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fahadabunayyan
 */
@Controller
public class IndexController 
{
    
    @RequestMapping("/index")
    public String getIndex()
    {
        
        return "index";
    }
    
    @RequestMapping("/about")
    public String getAbout()
    {
        return "about";
    }
    
    @RequestMapping("/media")
    public String getMedia()
    {
        return "media";
    }
    
    @RequestMapping("mediaConnect")
    public String getMediaConnect()
    {
        return "mediaConnect";
    }
    
}
