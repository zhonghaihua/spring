package com.mkyong.core;

//import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
import com.mkyong.user.Person;
import com.mkyong.user.ValueObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

public class App
{

	 private static final Log log = LogFactory.getLog(App.class);

     public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(new Mongo(), "database");

	    Person p=new Person("Joe",34);
	    
	    mongoOps.insert(p);
	    
	    //WriteResult writeResult=mongoOps.updateFirst(new Query(where("name").is("Joe")), update("age", 35), Person.class);
	    
	    log.info(mongoOps.updateFirst(new Query(where("name").is("Joe")), update("age", 35), Person.class));

	    log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));
        
	    MapReduceResults<ValueObject> result=mongoOps.mapReduce("docs", "classpath:map.js", "classpath:reduce.js", ValueObject.class);
		
		for(ValueObject valueObject:result)
		{
			System.out.println(valueObject);
		}
	    
	    mongoOps.remove(p);

	    System.out.println(mongoOps.getCollectionName(Person.class));
	    
	    mongoOps.dropCollection("person");
	    
	  }
}
