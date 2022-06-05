package com.twinkle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinkle.domain.Record;
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
	
}
