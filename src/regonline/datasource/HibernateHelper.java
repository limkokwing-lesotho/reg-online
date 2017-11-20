package regonline.datasource;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class HibernateHelper {

	private static Logger logger = LogManager.getLogger(HibernateHelper.class);
	
	private static final SessionFactory sessionFactory =
	                   new Configuration().
	                   configure("regonline/datasource/mysql.cfg.xml")
	                   	.buildSessionFactory();

	//inaccessible constructor
	private HibernateHelper() {
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException e) {
            	logger.error("Couldn't close Session ", e);
            }
        }
    }
}
