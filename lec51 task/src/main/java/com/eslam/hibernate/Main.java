package com.eslam.hibernate;

import com.eslam.hibernate.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory()) {

            Teacher teacher = new Teacher("Ahmed Ali", 18, "Cairo");

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(teacher);
                transaction.commit();
            }

            try (Session session = sessionFactory.openSession()) {
                List<Teacher> teachers = session
                        .createQuery("from Teacher", Teacher.class)
                        .getResultList();

                teachers.forEach(System.out::println);
            }
        }
    }
}
