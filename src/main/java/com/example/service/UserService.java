package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.pojo.User;
import com.example.pojo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	Map<Integer,User> users;
	
	public Map<Integer,User> showAllUser(){
		users = ur.findAll().stream().collect(  
	            Collectors.toMap(User::getId, (p) -> p));
		return users;
	}
	
	public User findById(int id) {
		return ur.findById(id);
	}
	
	public void deleteUser(int id) {
		ur.delete(id);
	}
	
	public void addUser(User user) {
		ur.saveAndFlush(user);
	}
}
