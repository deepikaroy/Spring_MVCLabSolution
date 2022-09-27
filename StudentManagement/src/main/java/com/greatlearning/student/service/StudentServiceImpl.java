package com.greatlearning.student.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.student.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService
{
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		try
		{
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException ex)
		{
			session = sessionFactory.openSession();	
		}
	}

	//Insert new records into the table.
	@Transactional
	public void save(Student student)
	{
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);		
		transaction.commit();
	}

	//Delete existing records from the table.
	@Transactional
	public void deleteById(int id)
	{
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.delete(student);
		transaction.commit();
	}	


	@Transactional
	public Student findById(int id)
	{
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();

		return student;
	}

	@Transactional
	public List<Student> findAll()
	{
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();		
		transaction.commit();

		return students;
	}

}
