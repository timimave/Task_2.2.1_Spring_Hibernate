package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.add(new User("Ivan", "Petrov", "Petrov@mail.ru")
            .setCar(new Car("Opel", 971)));

        userService.add(new User("Alexandr", "Smirnov", "Smirnov@mail.ru")
            .setCar(new Car("Suzuki", 100)));

        userService.add(new User("John", "Doe", "JohnDoe@mail.com")
            .setCar(new Car("BMW", 777)));

        userService.add(new User("Suzan", "Bakes", "Bakes@mail.com")
            .setCar(new Car("Ford", 444)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = [" + user.getCar() + "]");
            System.out.println();
        }

        User user = userService.getUserByCar("Opel", 971);
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = [" + user.getCar() + "]");
        System.out.println();

        context.close();
    }
}
