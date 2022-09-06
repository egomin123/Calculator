package Practika2.ModeliAndNews.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kolods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Kolods(String nazvanie, String opisanie, String firma, Integer kolvo, Integer kart) {
        this.nazvanie = nazvanie;
        this.opisanie = opisanie;
        this.firma = firma;
        this.kolvo = kolvo;
        this.kart = kart;
    }

    public Kolods() {

    }

    String nazvanie, opisanie, firma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnazvanie() {
        return nazvanie;
    }

    public void setnazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getopisanie() {
        return opisanie;
    }

    public void setopisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    public String getfirma() {
        return firma;
    }

    public void setfirma(String firma) {
        this.firma = firma;
    }

    public Integer getkolvo() {
        return kolvo;
    }

    public void setkolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    public Integer getkart() {
        return kart;
    }

    public void setkart(Integer kart) {
        this.kart = kart;
    }

    Integer kolvo, kart;

}
