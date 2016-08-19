package com.wyp.module.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wyp.module.common.ResponseEntity;
import com.wyp.module.pojo.Customer;
import com.wyp.module.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	/**
	 *
	 */
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin() {
		return "../index.jsp";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(Customer customer, HttpSession session) {
		ResponseEntity resEntity = new ResponseEntity();
		String destPage = "redirect:/manager/main";
		customer = userService.findCustomer4Login(customer);
		if (null != customer) {
			session.setAttribute("currentUser", customer);
			resEntity.setResCode("true");
		} else {
			resEntity.setResCode("false");
			resEntity.setErrMsg("login failed:name or password error,please try again!");
			destPage = "redirect:../login.jsp";
		}
		session.setAttribute("loginResponse", resEntity);
		return destPage;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userRegister(Customer customer, HttpSession session) {
		ResponseEntity resEntity = new ResponseEntity();
		String destPage = "redirect:/manager/main";
		customer = userService.findCustomer4Login(customer);
		if (null != customer) {
			session.setAttribute("currentUser", customer);
			resEntity.setResCode("true");
		} else {
			resEntity.setResCode("false");
			resEntity.setErrMsg("login failed:name or password error,please try again!");
			destPage = "redirect:../login.jsp";
		}
		session.setAttribute("loginResponse", resEntity);
		return destPage;
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public String uploads(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("chunk") == null) {

			String realPath = request.getSession().getServletContext().getRealPath("/uploaddir/");
			String fileName = file.getOriginalFilename();

			File targetFile = new File(realPath, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile); // С�ļ���ֱ�ӿ���
			return "";
		} else {
			int chunk = Integer.parseInt(request.getParameter("chunk")); // ��ǰ��Ƭ
			int chunks = Integer.parseInt(request.getParameter("chunks")); // ��Ƭ�ܼ�
			if (chunk == 0) {
				logger.info("start: " + new Date());
			}
			if (chunk == chunks - 1) {
				logger.info("end: " + new Date());
			}
			String realPath = request.getSession().getServletContext().getRealPath("/uploaddir/");

			String Ogfilename = file.getOriginalFilename();

			File tempFile = new File(realPath, Ogfilename);
			OutputStream outputStream = new FileOutputStream(tempFile, true);
			InputStream inputStream = file.getInputStream();

			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			return "�ϴ��ɹ�";
		}

	}
}
