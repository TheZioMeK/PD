package pl.edu.pk.user;

import pl.edu.pk.connector.HibernateUtil;


public class UserRepository {

    public static User getUserById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(User.class, id);
        transaction.commit();
        session.close();
        return result;
    }

    public static boolean addUser(String email, String phoneNumber, Integer time) {

        if(phoneNumber.length()!=9){
            return false;
        }

        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(new User(email,phoneNumber,time));
        transaction.commit();
        session.close();

        return true;

    }

    public static Long getUsersQuantity(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var quantity = session.createQuery("SELECT COUNT(*) FROM User user", Long.class).getSingleResult();

        transaction.commit();
        session.close();
        return quantity;
    }
}
