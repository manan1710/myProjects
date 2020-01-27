package com.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

	@Autowired
	private MongoOperations mongo;
	
	public int getNextSequence(String collectionName)
	{
		//get sequence id
		Query query = new Query(Criteria.where("_id").is(collectionName));
		
		//increment sequence id by 1
		Update update = new Update();
		update.inc("seq",1);
		
		//return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		
		CounterBean counterBean = mongo.findAndModify(query, update, options, CounterBean.class);
		
		return counterBean.getSeq();
	}
	
}
