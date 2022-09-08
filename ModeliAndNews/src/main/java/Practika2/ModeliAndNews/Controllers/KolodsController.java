package Practika2.ModeliAndNews.Controllers;


import Practika2.ModeliAndNews.Models.Kolods;
import Practika2.ModeliAndNews.Models.Menu;
import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.KolodsRepository;
import Practika2.ModeliAndNews.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Kolods> kolods = kolodsRepository.findById(id);
        ArrayList<Kolods> kolodsArrayList =  new ArrayList<>();
        kolods.ifPresent(kolodsArrayList::add);
        model.addAttribute("kolods", kolodsArrayList);
        return "Kolods/Info-Kolods";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Kolods kolods = kolodsRepository.findById(id).orElseThrow();
        kolodsRepository.delete(kolods);

        //newsRepository.deleteById(id);
        return "redirect:/Kolods/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!kolodsRepository.existsById(id) )
        {
            return "redirect:/Kolods/";
        }

        Optional<Kolods> kolods = kolodsRepository.findById(id);
        ArrayList<Kolods> kolodsArrayList =  new ArrayList<>();
        kolods.ifPresent(kolodsArrayList::add);
        model.addAttribute("Kolods", kolodsArrayList);
        model.addAttribute("nazvanie", kolods.get().getnazvanie());
        model.addAttribute("kart", kolods.get().getkart());
        model.addAttribute("kolvo", kolods.get().getkolvo());
        model.addAttribute("opisanie", kolods.get().getopisanie());
        model.addAttribute("firma", kolods.get().getfirma());

        return "Kolods/Edit-Kolods";
    }



    @PostMapping("/edit/{id}")
    public String editKolods(
            @PathVariable("id") Long id,
            @RequestParam("nazvanie") String nazvanie,
            @RequestParam("opisanie") String opisanie,
            @RequestParam("firma") String firma,
            @RequestParam("kolvo") Integer kolvo,
            @RequestParam("kart") Integer kart,
            Model model
    )
    {
        if (!kolodsRepository.existsById(id) )
        {
            return "redirect:/Kolods/";
        }
        if ( nazvanie.isEmpty() || opisanie.isEmpty() || firma.isEmpty() || kolvo.equals(null) || kart.equals(null))
        {
            return "redirect:/Kolods/";
        }
        Kolods kolods = kolodsRepository.findById(id).orElseThrow();


        kolods.setnazvanie(nazvanie);
        kolods.setopisanie(opisanie);
        kolods.setfirma(firma);
        kolods.setkolvo(kolvo);
        kolods.setkart(kart);

        kolodsRepository.save(kolods);
        return "redirect:/Kolods/";
    }

}
