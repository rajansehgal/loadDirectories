package rajan.testing.file.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import rajan.testing.file.Util.HibernateUtil;
import rajan.testing.file.persistence.DirStructure;
import rajan.testing.file.persistence.FileDetails;

public class HibernateDAOImpl {


	
	public void updateDir(List<DirStructure> table) {
		Session session = HibernateUtil.createSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (DirStructure d: table) {
			session.saveOrUpdate(d);
		}
		tx.commit();

	}
	
	public void updateFile(List<FileDetails> table) {
		Session session = HibernateUtil.createSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();
		for (FileDetails f: table) {
			session.saveOrUpdate(f);
		}
		tx.commit();

	}
	
	
}
