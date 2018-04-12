/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author fahadabunayyan
 */

@Controller
public class SearchController 
{

    @RequestMapping("/search")
    public String getSearch()
    {
        return "search";
    }
    
    @PostMapping("/search")
    public @ResponseBody String returnSearch(@RequestParam String search
                        , @RequestParam String option) 
        {
            return "<html><h1>Successful</h1><meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/searchResult\" /></html>";
            
        }
    @RequestMapping("/searchResult")
    public String getResult()
    {
        return "searchResult";
    }
    
}
