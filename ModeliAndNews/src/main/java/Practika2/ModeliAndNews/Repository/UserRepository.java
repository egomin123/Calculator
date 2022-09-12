package Practika2.ModeliAndNews.Repository;


import Practika2.ModeliAndNews.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByLogin(String login);
}
