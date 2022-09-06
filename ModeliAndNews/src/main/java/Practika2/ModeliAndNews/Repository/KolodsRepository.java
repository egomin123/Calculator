package Practika2.ModeliAndNews.Repository;

import Practika2.ModeliAndNews.Models.Kolods;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KolodsRepository extends CrudRepository<Kolods, Long> {

    public List<Kolods> findByNazvanie(String Nazvanie);
    public List<Kolods> findByNazvanieContains(String nazvanie);
}