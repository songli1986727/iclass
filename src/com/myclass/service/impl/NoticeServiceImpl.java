package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.NoticeDAO;
import com.myclass.entity.CramClass;
import com.myclass.entity.Notice;
import com.myclass.service.NoticeService;
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO<Notice,Integer> noticeDAO;
	
	public List<Notice> queryByClassId(Integer classId,Integer page,Integer rows) {
		List<Notice> noticeList = new ArrayList<Notice>();
		try{			
			noticeList = noticeDAO.findByProperty(Notice.class, "classId", classId, "id", "DESC", page, rows);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return noticeList;
	}
	
	public Notice queryById(Integer id){
		try{
			return noticeDAO.get(Notice.class, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void saveNotice(Notice notice){
		try{
			noticeDAO.persist(notice);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Notice> queryByUserNotices(Set<CramClass> classes,Integer page,Integer rows){
		List<Notice> noticeList = new ArrayList<Notice>();
		try{
			Iterator<CramClass> it = classes.iterator();
			while(it.hasNext()){
				CramClass clas = it.next();
				List<Notice> notices = queryByClassId(clas.getId(), page, rows);
				noticeList.addAll(notices);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return noticeList;
	}
}
