package com.twinkle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.twinkle.domain.Record;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;
import com.twinkle.mapper.RecordMapper;
import com.twinkle.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {
	
	
	@Autowired
	private RecordMapper recordMapper;
	
	@Override
	public Integer addRecord(Record record) {
		return recordMapper.addRecord(record);
	}

	@Override
	public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
		//设置分页的参数 开始分页
		PageHelper.startPage(pageNum,pageSize);
		//如果不是管理员，则查询当前借阅的人
		if(!"ADMIN".equals(user.getRole())){
			record.setBorrower(user.getName());
		}
		Page<Record> page = recordMapper.searchRecords(record);
		
		return new PageResult(page.getTotal(),page.getResult());
	}
	
}
