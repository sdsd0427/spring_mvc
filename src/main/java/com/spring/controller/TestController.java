package com.spring.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {}
	
	@RequestMapping(value = "/test/param", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response) {
		String url = "test/main";
		
		String message = request.getParameter("message");
		
		request.setAttribute("message", message);
		return url;
	}
	
	@RequestMapping(value = "/test2/param", method = RequestMethod.GET)
	public String test2Param(@RequestParam(name = "message", defaultValue = "Nice Day!!") String a, Model model) {
		String url = "test/main";
		
		model.addAttribute("message", a);
		
		return url;
	}
	
	@RequestMapping(value = "/test/main", method = RequestMethod.GET)
	public void test4Param(@ModelAttribute("message") String message) {}
	
	@RequestMapping(value = "/test/member", method = RequestMethod.GET)
	public String testMember(MemberVO member) {
		
		System.out.println(member);
		
		return null;
	}
}



class MemberVO{
	int num;
	String id;
	String pwd;
	String name;
	String phone;
	Date regDate;
	
	
	public Date getRegDate() {
		return regDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone
				+ ", regDate=" + regDate + "]";
	}

	
}