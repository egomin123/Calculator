package Practika2.ModeliAndNews.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Menu {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        public Menu(String bludo, String opisanie, String ingridient, Integer cena, Integer kkal) {
            this.bludo = bludo;
            this.opisanie = opisanie;
            this.ingridient = ingridient;
            this.cena = cena;
            this.kkal = kkal;
        }

        public Menu() {

        }

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
        String bludo;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше",min = 3,max=10000)
        String opisanie;

    @NotEmpty(message = "Впишите, пожалуйста, ингридиенты")
        String ingridient;


    @Min(message = "Количество не может быть отрицательным",value = 0)
    @Max(message = "Количество не должно превышать 100000",value = 100000)
    @NotNull(message = "Число не должно быть пустым")
    Integer cena;

    @Min(message = "Количество не может быть отрицательным",value = 0)
    @Max(message = "Количество не должно превышать 100000",value = 100000)
    @NotNull(message = "Число не должно быть пустым")
    Integer kkal;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getbludo() {
            return bludo;
        }

        public void setbludo(String bludo) {
            this.bludo = bludo;
        }

        public String getopisanie() {
            return opisanie;
        }

        public void setopisanie(String opisanie) {
            this.opisanie = opisanie;
        }

        public String getingridient() {
            return ingridient;
        }

        public void setingridient(String ingridient) {
            this.ingridient = ingridient;
        }

        public Integer getcena() {
            return cena;
        }

        public void setcena(Integer cena) {
            this.cena = cena;
        }

        public Integer getkkal() {
            return kkal;
        }

        public void setkkal(Integer kkal) {
            this.kkal = kkal;
        }


}