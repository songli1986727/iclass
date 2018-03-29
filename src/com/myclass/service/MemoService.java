package com.myclass.service;

import java.util.List;

import com.myclass.entity.Memo;

public interface MemoService {
	
	public void addMemo(Memo memo);
	
	public List<Memo> queryByUserId(Integer userId,Integer page);
	
	public Memo queryById(Integer id);

}
