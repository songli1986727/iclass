package com.myclass.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myclass.entity.Notice;
import com.myclass.entity.User;
import com.myclass.service.NoticeService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private NoticeService noticeService;
		
	@RequestMapping("/list")
	public String noticeList(ModelMap modelMap,HttpSession session){
		LOGGER.info("通知列表");
		List<Notice> noticeList = new ArrayList<Notice>();
		try{
			User user = (User)session.getAttribute("user");
			if(user!=null){
				noticeList = noticeService.queryByUserNotices(user.getClasses(),0,0);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		modelMap.addAttribute("noticeList", noticeList);
		return "/notice_list";
	}

	@RequestMapping("/detail")
	public String noticeDetail(ModelMap modelMap,Integer id){
		try{
			Notice notice = noticeService.queryById(id);
			modelMap.addAttribute("notice", notice);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/notice_detail";
	}
	
	@RequestMapping("/toEdit")
	public String toEditNotice(ModelMap modelMap){
		return "/notice_edit";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String, String> saveEditNotice(@RequestBody Notice notice,HttpServletRequest request){
		Map<String,String> map= new HashMap<String,String>();
		try{
			LOGGER.info("保存通知");
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
			String nowTime = df.format(new Date());
			User user = (User)request.getSession().getAttribute("user");
			notice.setSendTime(nowTime);
			notice.setSenderId(user.getId());
			notice.setSenderName(user.getUserName());
			noticeService.saveNotice(notice);
			
			map.put("msg", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
