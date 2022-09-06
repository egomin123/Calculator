package Practika2.ModeliAndNews.Controllers;


import Practika2.ModeliAndNews.Models.Kolods;
import Practika2.ModeliAndNews.Models.Menu;
import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.KolodsRepository;
import Practika2.ModeliAndNews.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Kolods")
public class KolodsController {


    @Autowired
    private KolodsRepository kolodsRepository;


    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Kolods> kolods =  kolodsRepository.findAll();
        model.addAttribute("kolods", kolods);
        return "Kolods/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {

        return "Kolods/AddKolods";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("nazvanie") String nazvanie,
            @RequestParam("opisanie") String opisanie,
            @RequestParam("firma") String firma,
            @RequestParam("kolvo") Integer kolvo,
            @RequestParam("kart") Integer kart,
            Model model)
    {
        Kolods kolodsOne = new Kolods(nazvanie, opisanie, firma, kolvo, kart);
        kolodsRepository.save(kolodsOne);
        return "redirect:/Kolods/";
    }


    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("Nazvanie") String nazvanie,
            Model model)
    {
       List<Kolods> kolodsList = kolodsRepository.findByNazvanie(nazvanie);
       model.addAttribute("kolods", kolodsList);
        return "Kolods/Index";
    }


    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("Nazvanie") String nazvanie,
            Model model)
    {
        List<Kolods> menuList = kolodsRepository.findByNazvanieContains(nazvanie);
        model.addAttribute("kolods", menuList);
        return "Kolods/Index";
    }
}
