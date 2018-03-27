/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author fahadabunayyan
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    List<User> findByUserName(String username);
    List<User> findByEmail(String email);
//    User findByUsername(String username);
}
