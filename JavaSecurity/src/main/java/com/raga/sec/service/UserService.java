/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.sec.service;

import com.raga.sec.entity.User;

/**
 *
 * @author raga
 */
public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}