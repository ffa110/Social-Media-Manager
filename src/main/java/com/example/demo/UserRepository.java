/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author fahadabunayyan
 */
public interface UserRepository extends CrudRepository<User, Long>
{
 
    List<User> findByUserName(String username);
    
}
