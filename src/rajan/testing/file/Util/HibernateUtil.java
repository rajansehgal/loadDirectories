package rajan.testing.file.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final HibernateUtil INSTANCE=new HibernateUtil();
	private static SessionFactory sessionFactory ;
	private static ServiceRegistry serviceRegistry;
	private HibernateUtil(){
		
		Configuration configuration = new Configuration();
	    configuration.configure("/resources/hibernate.cfg.xml");
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	    sessionFactory = configuration.addPackage("rajan.testing.file.persistence").buildSessionFactory(serviceRegistry);
	   
	}

	public static HibernateUtil getInstance(){
		return INSTANCE;
	}
	public static SessionFactory createSessionFactory() {
	  	    return sessionFactory;
	}
	
	
}
