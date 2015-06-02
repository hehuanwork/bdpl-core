package com.qinghuo.bdpl.core.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qinghuo.bdpl.core.domain.User;
import com.qinghuo.bdpl.core.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

	@Resource
	private UserService userService;
	@RequestMapping("/tolist")
	public ModelAndView tolist() throws Exception{
		ModelAndView mv = new ModelAndView();
		List<User> users = userService.findAllUser();
		mv.addObject("userList", users);
		mv.setViewName("user/to_list");
		return mv;
	}
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(@ModelAttribute("id")String id) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = userService.findUserById(id);
		mv.addObject("user", user);
		mv.setViewName("user/to_update");
		return mv;
	}
	@RequestMapping("/update")
	public ModelAndView processUpdate(@ModelAttribute("user") User user) throws Exception{
		ModelAndView mv = new ModelAndView();
		userService.updateUser(user);
		mv.setViewName("user/to_list");
		return mv;
	}
	@RequestMapping("/delete")
	public ModelAndView processDelete(@ModelAttribute("id")String id) throws Exception {
		ModelAndView mv = new ModelAndView();
		userService.deleteUser(id);
		mv.setViewName("user/to_list");
		return mv;
	}
	@RequestMapping("/jsonlist")
	@ResponseBody
	public List<User> getJsonForUserList() throws Exception{
		List<User> users = userService.findAllUser();
		return users;
	}
	
}
