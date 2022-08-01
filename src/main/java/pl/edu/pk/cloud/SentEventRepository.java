package pl.edu.pk.cloud;

import pl.edu.pk.cloud.SentEvent;
import pl.edu.pk.connector.HibernateUtil;

import java.util.Date;

public class SentEventRepository {

    public static boolean addSentEvent(String email, Date time) {



        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(new SentEvent(email,time));
        transaction.commit();
        session.close();

        return true;

    }
}
