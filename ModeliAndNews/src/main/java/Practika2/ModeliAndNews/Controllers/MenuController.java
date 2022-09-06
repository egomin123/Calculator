package Practika2.ModeliAndNews.Controllers;


import Practika2.ModeliAndNews.Models.Menu;
import Practika2.ModeliAndNews.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;


    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Menu> menu =  menuRepository.findAll();
        model.addAttribute("menu", menu);
        return "Menu/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {

        return "Menu/AddMenu";
    }


    @PostMapping("/add")
    public String add(
            @RequestParam("bludo") String bludo,
            @RequestParam("opisanie") String opisanie,
            @RequestParam("ingridient") String ingridient,
            @RequestParam("cena") Integer cena,
            @RequestParam("kkal") Integer kkal,
            Model model)
    {
        Menu menuOne = new Menu(bludo, opisanie, ingridient, cena, kkal);
        menuRepository.save(menuOne);
        return "redirect:/Menu/";
    }



    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Bludo") String bludo,
            Model model)
    {
        List<Menu> menuList = menuRepository.findByBludo(bludo);
        model.addAttribute("menu", menuList);
        return "Menu/Index";
    }


    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Bludo") String bludo,
            Model model)
    {
        List<Menu> menuList = menuRepository.findByBludoContains(bludo);
        model.addAttribute("menu", menuList);
        return "Menu/Index";
    }
}
