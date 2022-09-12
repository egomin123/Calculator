package Practika2.ModeliAndNews.Controllers;

import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<News> news =  newsRepository.findAll();
        model.addAttribute("news", news);
        return "News/Index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        model.addAttribute("news", new News());
        return "News/AddNews";
    }





    @GetMapping("/Search")
        public String GetAdd(
                @RequestParam("title") String title,
                Model model)
        {
            List<News> newsList = newsRepository.findByTitle(title);
            model.addAttribute("news", newsList);
            return "News/Index";
        }


    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("title") String title,
            Model model)
    {
        List<News> newsList = newsRepository.findByTitleContains(title);
        model.addAttribute("news", newsList);
        return "News/Index";
    }




    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> newsArrayList =  new ArrayList<>();
        news.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList);
        return "News/Info-News";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);

        return "redirect:/News/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!newsRepository.existsById(id) )
        {
            return "redirect:/News/";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> newsArrayList =  new ArrayList<>();
        news.ifPresent(newsArrayList::add);
        model.addAttribute("news", newsArrayList.get(0));
        model.addAttribute("News", newsArrayList);
        model.addAttribute("title", news.get().getTitle());
        model.addAttribute("author", news.get().getAuthor());
        model.addAttribute("body_text", news.get().getBody_text());
        model.addAttribute("views", news.get().getViews());
        model.addAttribute("likes", news.get().getLikes());
        return "News/Edit-News";

    }

    @PostMapping("/add")
    public String add(
           @ModelAttribute("news") @Valid News newNews,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "News/AddNews";

        newsRepository.save(newNews);
        return "redirect:/News/";
    }




    @PostMapping("/edit/{id}")
    public String editNews(
            @PathVariable("id") Long id,

            @ModelAttribute("news") @Valid News newNews,
            BindingResult bindingResult,
            Model model)
    {

        if (!newsRepository.existsById(id) )
        {
            return "redirect:/News/";
        }

        if(bindingResult.hasErrors())
            return "News/Edit-News";
        newNews.setId(id);
      //  News news = newsRepository.findById(id).orElseThrow();
        newsRepository.save(newNews);
        return "redirect:/News/";
    }


}
