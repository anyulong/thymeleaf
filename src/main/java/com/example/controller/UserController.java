package com.example.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pojo.User;
import com.example.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService us;
	Map<Integer,User> users;
	
	@RequestMapping("/toindex")
	public String showUsers(Model model) {
		System.out.println("请求了");
		users=us.showAllUser();
		model.addAttribute("users",users);
		return "index";
	}
	
	@RequestMapping("/change")
	public String changeMethod(@RequestParam(value="id",defaultValue="0") int id,Model model) {
		if(id==0) {
			model.addAttribute("id",id);
			return "change";
		}
		User user=users.get(id);
		model.addAttribute("user",user);
		model.addAttribute("id",id);
		return "change";
	}
	
	@RequestMapping("/dochange")
	public String changeMethod(User user) {
	/*	if(users.get(user.getId())!=null) {
			users.remove(user.getId());
		}*/
		if(us.findById(user.getId())!=null) {
			users.remove(user.getId());
			us.deleteUser(user.getId());
		}
		users.putIfAbsent(user.getId(), user);
		us.addUser(user);
		return "redirect:toindex";
	}
}
