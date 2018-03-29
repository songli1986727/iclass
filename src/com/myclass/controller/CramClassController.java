package com.myclass.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.CramClass;
import com.myclass.service.CramClassService;
@Controller
@RequestMapping("/class")
public class CramClassController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private CramClassService cramClassService;
	
	@RequestMapping("/save")
	@ResponseBody
	public  Map<String, String> saveCramClass(HttpSession session,String name,Integer grade,String subject){
		Map<String,String> map= new HashMap<String,String>();
		try{
			LOGGER.info("保存班级");
			//User teacher = (User)session.getAttribute("user");
			CramClass cramClass =new CramClass();
			cramClass.setClassName(URLDecoder.decode(name, "UTF-8"));
			cramClass.setGrade(grade);
			cramClass.setClassStatus(0);
			//cramClass.setTeacherId(teacher.getId());
			cramClass.setClassType(URLDecoder.decode(subject, "UTF-8"));
			cramClass.setCreateTime(new Date());
			cramClassService.saveCramClass(cramClass);
			map.put("msg", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/toEdit")
	public String toEditClass(ModelMap modelMap){
		return "/class_edit";
	}

	@RequestMapping("/list")
	public String classList(ModelMap modelMap,HttpSession session){
		try{
			List<CramClass> classList = new ArrayList<CramClass>();
			//User teacher = (User)session.getAttribute("user");
			classList = cramClassService.queryByAll();
			modelMap.addAttribute("classList", classList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/class_list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,String> deleteClass(Integer id){
		Map<String,String> map= new HashMap<String,String>();
		try{
			cramClassService.deleteClass(id);
			map.put("msg", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
