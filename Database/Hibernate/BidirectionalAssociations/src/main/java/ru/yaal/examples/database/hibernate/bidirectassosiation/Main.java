package ru.yaal.examples.database.hibernate.bidirectassosiation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            saveEntities();
            loadEntities();
        } finally {
            SessionFactory sessionFactory = Factory.getSessionFactory();
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    private static void loadEntities() throws Exception {
        Session session = Factory.getSessionFactory().openSession();
        List<Slip> allSlips = session.createCriteria (Slip.class).list();
        session.close();
    }

    private static void saveEntities() throws Exception {
        Session session = Factory.getSessionFactory().openSession();

        Payment payment = new Payment("Bike buy");

        Transaction transaction = new Transaction(payment);

        Slip slipA = new Slip(transaction, "С Вас 1 000 $$$");
        Slip slipB = new Slip(transaction, "С Вас $10 000 000");

        session.beginTransaction();
        session.save(payment);
        session.save(transaction);
        session.save(slipA);
        session.save(slipB);
        session.getTransaction().commit();
        session.flush();
    }
}
