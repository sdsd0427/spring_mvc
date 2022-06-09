package com.spring.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.command.FileUploadCommand;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileUploadForm")
	public void fileUpload() {}
	
	@RequestMapping(value = "/multipartFile", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView uploadByMultipartFile(String title, MultipartFile file, ModelAndView mnv) throws Exception{
		//파일 저장 폴더 설정
		String uploadPath = "c:\\upload\\file";
		
		//파일명
		String fileName = file.getOriginalFilename();
		
		//파일 경로 확인 및 생성
		File upload = new File(uploadPath, fileName);
		upload.mkdirs();
		
		//파일 저장
		file.transferTo(upload);
		
		mnv.addObject("title", title);
		mnv.addObject("originalFileName", file.getOriginalFilename());
		mnv.addObject("uploadedFileName", upload.getName());
		mnv.addObject("uploadPath", uploadPath);
		mnv.setViewName("fileUploaded");
		
		return mnv;
	}
	
	@RequestMapping(value = "/multipartHttpServletRequest", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView uploadByMultipartHttpServletRequest(MultipartHttpServletRequest requst, ModelAndView mnv) throws Exception{
		String title = requst.getParameter("title");
		MultipartFile multi = requst.getFile("file");
		
		mnv = uploadByMultipartFile(title, multi, mnv);
		
		return mnv;
	}
	
	@RequestMapping(value = "/commandObject", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView uploadByCommandObject(FileUploadCommand command, ModelAndView mnv) throws Exception{
		
		MultipartFile multi = command.getFile();
		String title = command.getTitle();
		
		mnv = uploadByMultipartFile(title, multi, mnv);
		return mnv;
	}
}
