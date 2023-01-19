package it.proactivity.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtility {
    public static Session createSession() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void checkSession(Session session) {
        if (!session.isOpen()) {
            session = createSession();
        }
        session.getTransaction();
    }

    public static void endSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
