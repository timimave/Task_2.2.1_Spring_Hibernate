package hiber.dao;

import hiber.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
            .createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        String hql = "from User user where user.car.model = :model and user.car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model",model).setParameter("series",series);

        return query.setMaxResults(1).getSingleResult(); //
    }

}

/*Если вы не вызовете метод setMaxResults(1),
то ваш запрос вернет все пользователей,
 у которых совпадают модель и серия автомобиля.
 Это может быть нежелательно, потому что это приведет
 к большому количеству лишних данных, которые вы хотите избежать.
 */