package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

import static jm.task.core.jdbc.util.Util.sessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }
    private static long usersCount;

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "create table if not exists users " +
                "(id bigint not null auto_increment primary key, " +
                "name varchar(50) not null, lastName varchar(50) not null, " +
                "age tinyint not null)";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "drop table if exists users";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "insert into users (id, name, lastName, age)" +
                " values ('" + ++usersCount + "', '" + name + "'," + " '" + lastName + "', " + " '" + age + "')";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "delete from users where id = " + id;
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select * from users";
        Query<User> query = session.createSQLQuery(sql).addEntity(User.class);
        List<User> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session= sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "delete from users";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
