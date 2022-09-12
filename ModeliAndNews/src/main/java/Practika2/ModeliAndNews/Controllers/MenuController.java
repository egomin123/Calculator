package Practika2.ModeliAndNews.Controllers;


import Practika2.ModeliAndNews.Models.Menu;
import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("menu", new Menu());
        return "Menu/AddMenu";
    }


    @PostMapping("/add")
    public String add(
            @ModelAttribute("menu") @Valid Menu newMenu,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "Menu/AddMenu";

        menuRepository.save(newMenu);
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


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> menuArrayList =  new ArrayList<>();
        menu.ifPresent(menuArrayList::add);
        model.addAttribute("menu", menuArrayList);
        return "Menu/Info-Menu";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);

        //newsRepository.deleteById(id);
        return "redirect:/Menu/";
    }



    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!menuRepository.existsById(id) )
        {
            return "redirect:/News/";
        }
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> menuArrayList =  new ArrayList<>();
        menu.ifPresent(menuArrayList::add);
        model.addAttribute("menu", menuArrayList.get(0));
        model.addAttribute("Menu", menuArrayList);
        model.addAttribute("bludo", menu.get().getbludo());
        model.addAttribute("ingridient", menu.get().getingridient());
        model.addAttribute("kkal", menu.get().getkkal());
        model.addAttribute("opisanie", menu.get().getopisanie());
        model.addAttribute("cena", menu.get().getcena());

        return "Menu/Edit-Menu";
    }


    @PostMapping("/edit/{id}")
    public String editMenu(
            @PathVariable("id") Long id,

            @ModelAttribute("menu") @Valid Menu newmenu,
            BindingResult bindingResult,
            Model model)
    {
        if (!menuRepository.existsById(id) )
        {
            return "redirect:/Menu/";
        }
        if(bindingResult.hasErrors())
            return "Menu/Edit-Menu";
        newmenu.setId(id);
        //  News news = newsRepository.findById(id).orElseThrow();
        menuRepository.save(newmenu);
        return "redirect:/Menu/";
    }
}
