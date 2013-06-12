package test;

import model.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

public class App {

	public static final Log log=LogFactory.getLog(App.class);
	
	public static void main(String[] args) throws Exception
	{
		//在读取xml文件时跟读取用java类的注解生成的配置有点不同，需要用不同的类
		ApplicationContext ctx=new GenericXmlApplicationContext("mongo.xml");
		
		Mongo mongo=(Mongo)ctx.getBean("mongo");
		
		MongoOperations mongoOps=new MongoTemplate(mongo,"database");
		
		mongoOps.insert(new Person("Joe", 34));

	    log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));

	    System.out.println(mongoOps.getCollectionName(Person.class));
	    
	    mongoOps.dropCollection("person");
	}
}
