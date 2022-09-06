package Practika2.ModeliAndNews.Repository;

import Practika2.ModeliAndNews.Models.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    public List<Menu> findByBludo(String bludo);
    public List<Menu> findByBludoContains(String bludo);

}
