package config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.Mongo;

/*
 * 当将xml文件配置转移到一个或多个java类的时候，即就是在某个java类加上@Configuration，一定要导入cglib和asm这两个jar包，不然会报错的。
 */
@Configuration
public class Appconfig {

	  /*
	   * Use the standard Mongo driver API to create a com.mongodb.Mongo instance.
	   */
	   public @Bean Mongo mongo() throws UnknownHostException {
	       return new Mongo("localhost");
	   }
	
}
