package com.wyp.module.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wyp.module.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String userLogin() {
		userService.showList();
		logger.info("go to main");
		return "main";
	}
}
