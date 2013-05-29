package test;

import model.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

import config.Appconfig;

public class App {
	
	private static final Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) throws Exception {
    	
    	//这个就是将xml配置文件转化为java类的配置形式后的用法，如果我们要get到java定义的那个bean的话一定要拿到那份配置配件，也就是把你从
    	//xml变成java类之后的那个类在AnnotationConfigApplicationContext里面注册
    	AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext(Appconfig.class);
    /*  也可以写成这样：
        AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext();
    	ctx.register(Appconfig.class);
    	ctx.refresh();
     */
    	
    	Mongo mongo = ctx.getBean(Mongo.class);

	    MongoOperations mongoOps = new MongoTemplate(mongo,"database");

	    mongoOps.insert(new Person("Joe", 34));

	    log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));

	    System.out.println(mongoOps.getCollectionName(Person.class));
	    
	    mongoOps.dropCollection("person");
	    
	  }
}
