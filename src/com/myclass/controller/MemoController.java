package com.myclass.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Memo;
import com.myclass.entity.User;
import com.myclass.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	@Autowired
	private MemoService memoService;

	@RequestMapping("/save")
	public String saveMemo(String content,HttpSession session){
		User user = (User)session.getAttribute("user");
		Memo memo = new Memo();
		try {
			System.out.println(URLDecoder.decode(content, "UTF-8"));
			memo.setContent(URLDecoder.decode(content, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		memo.setUserId(user.getId());
		memoService.addMemo(memo);
		return "redirect:/user/teacher.htmls";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(){
		return "/memo_edit";
	}
	
	@RequestMapping("/detail")
	public String detail(Integer id,ModelMap modelMap){
		try{
			Memo memo = memoService.queryById(id);
			modelMap.put("memo", memo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/memo_detail";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap,HttpSession session){
		User user = (User)session.getAttribute("user");
		List<Memo> memoList = memoService.queryByUserId(user.getId(),0);
		modelMap.addAttribute("memoList",memoList);
		return "/memo_list";
	}
}
