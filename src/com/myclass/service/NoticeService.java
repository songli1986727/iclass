package com.myclass.service;

import java.util.List;
import java.util.Set;

import com.myclass.entity.CramClass;
import com.myclass.entity.Notice;

public interface NoticeService {
	
	public List<Notice> queryByClassId(Integer classId,Integer page,Integer rows);
	
	public Notice queryById(Integer id);
	
	public void saveNotice(Notice notice);
	
	public List<Notice> queryByUserNotices(Set<CramClass> classes,Integer page,Integer rows);

}
