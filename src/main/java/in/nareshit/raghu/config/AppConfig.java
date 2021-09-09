package in.nareshit.raghu.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan("in.nareshit.raghu")
@PropertySource("classpath:app.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public DriverManagerDataSource dsobj() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("my.db.driver"));
		ds.setUrl(env.getProperty("my.db.url"));
		ds.setUsername(env.getProperty("my.db.username"));
		ds.setPassword(env.getProperty("my.db.password"));
		
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sfObj() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dsobj());
		sf.setPackagesToScan("in.nareshit.raghu.entity");
		sf.setHibernateProperties(props());
		return sf;
	}
	
	private Properties props() {
		Properties p = new Properties();
		p.put("hibernate.dialect", env.getProperty("my.orm.dialect"));
		p.put("hibernate.show_sql", env.getProperty("my.orm.show"));
		p.put("hibernate.format_sql", env.getProperty("my.orm.fmt"));
		p.put("hibernate.hbm2ddl.auto",env.getProperty("my.orm.ddl"));
		return p;
	}

	@Bean
	public HibernateTemplate htObj() {
		HibernateTemplate ht = new HibernateTemplate();
		ht.setSessionFactory(sfObj().getObject());
		return ht;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager htx = new HibernateTransactionManager();
		htx.setSessionFactory(sfObj().getObject());
		return htx;
	}
	
	
}
