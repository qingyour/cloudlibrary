package com.twinkle.mapper;

import com.github.pagehelper.Page;
import com.twinkle.domain.Record;

public interface RecordMapper {
	Integer addRecord(Record record);   
	
	Page<Record> searchRecords(Record record);
	
}
