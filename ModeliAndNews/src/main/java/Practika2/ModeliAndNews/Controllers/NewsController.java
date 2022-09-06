package Practika2.ModeliAndNews.Controllers;

import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


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

        return "News/AddNews";
    }
    @PostMapping("/add")
    public String add(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("body_text") String body_text,
            @RequestParam("views") Integer views,
            @RequestParam("likes") Integer likes,
            Model model)
    {
        News newsOne = new News(title, body_text, author, views, likes);
        newsRepository.save(newsOne);
        return "redirect:/News/";
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
}
