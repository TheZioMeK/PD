package pl.edu.pk.cloud;

import pl.edu.pk.connector.HibernateUtil;

import java.util.Date;

public class UserStatRepository {
    public static boolean addUserStat(Long userQuantity, Date UserStatDate) {



        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(new UserStat(userQuantity,UserStatDate));
        transaction.commit();
        session.close();

        return true;

    }
}
