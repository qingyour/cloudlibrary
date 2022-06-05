package com.twinkle.service;

import com.twinkle.domain.Record;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;

public interface RecordService {
	Integer addRecord(Record record);
	PageResult searchRecords(Record record,User user,Integer pageNum,Integer pageSize);
}
