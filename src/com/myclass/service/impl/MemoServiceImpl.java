package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dao.MemoDAO;
import com.myclass.entity.Memo;
import com.myclass.service.MemoService;
import com.myclass.util.DateUtil;
@Service("memoService")
public class MemoServiceImpl implements MemoService {
	@Autowired
	private MemoDAO<Memo,Integer> memoDAO;

	public void addMemo(Memo memo) {
		try{
			memo.setDate(DateUtil.getFormatDate("yyyy-MM-dd"));
			memo.setIndex(queryIndexByDate(memo.getDate()));
			memoDAO.save(memo);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Memo> queryByUserId(Integer userId,Integer page) {
		List<Memo> memoList = new ArrayList<Memo>();
		try{
			if(page>0){
				memoList = memoDAO.findByProperty(Memo.class, "userId", userId, "id", "desc",page,8);
			}else{
				memoList = memoDAO.findByProperty(Memo.class, "userId", userId, "id", "desc",null,null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return memoList;
	}

	public Integer queryIndexByDate(String date){
		Integer index = 0;
		try{
			List<Memo> memoList = memoDAO.findByProperty(Memo.class, "date", date, "index", "desc");
			if(memoList!=null&&memoList.size()>0){
				index =  memoList.get(0).getIndex();
			}		
			index = index+1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return index;
	}
	
	public Memo queryById(Integer id){
		try{
			return memoDAO.get(Memo.class, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
