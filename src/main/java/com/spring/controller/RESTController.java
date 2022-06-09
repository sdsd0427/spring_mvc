package com.spring.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RESTController {

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	{
		dataMap.put("abc", "123");
		dataMap.put("ㄱㄴㄷ", "123");
		
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("a", "1");
		tempMap.put("b", "2");
		tempMap.put("c", "3");
		tempMap.put("d", "4");
		
		dataMap.put("temp", tempMap);
	}
	
//	@RequestMapping(value = "/rest/old", method = RequestMethod.GET)
//	public void restOld(HttpServletResponse response) throws Exception{
//		//출력
//		ObjectMapper mapper = new ObjectMapper();
//		
//		//content Type 결정
//		response.setContentType("application/json;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		//내보내기
//		out.println(mapper.writeValueAsString(dataMap));
//		
//		//환원
//		out.close();
//	}
	
//	@RequestMapping(value = "/rest/spring", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> restSpring(){
//		return dataMap;
//	}
	
	@RequestMapping(value = "/rest/spring", method = RequestMethod.GET)
	public Map<String, Object> restSpring(){
		return dataMap;
	}
	
	@RequestMapping(value = "/rest/best", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> restSpringBest(){
		
		ResponseEntity<Map<String, Object>> result = null;
		
		try {
			result = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<Map<String,Object>>(dataMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
}
