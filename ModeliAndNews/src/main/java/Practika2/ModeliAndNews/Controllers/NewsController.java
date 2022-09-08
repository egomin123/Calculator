package Practika2.ModeliAndNews.Controllers;

import Practika2.ModeliAndNews.Models.News;
import Practika2.ModeliAndNews.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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

        //newsRepository.deleteById(id);
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
        model.addAttribute("News", newsArrayList);
        model.addAttribute("title", news.get().getTitle());
        model.addAttribute("author", news.get().getAuthor());
        model.addAttribute("body_text", news.get().getBody_text());
        model.addAttribute("views", news.get().getViews());
        model.addAttribute("likes", news.get().getLikes());

        return "News/Edit-News";
    }


    @PostMapping("/edit/{id}")
    public String editNews(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("body_text") String body_text,
            @RequestParam("views") Integer views,
            @RequestParam("likes") Integer likes,
            Model model
    )
    {
        if (!newsRepository.existsById(id) )
        {
            return "redirect:/News/";
        }
        if ( title.isEmpty() || author.isEmpty() || body_text.isEmpty() || views.equals(null) || likes.equals(null))
        {
            return "redirect:/Kolods/";
        }
       News news = newsRepository.findById(id).orElseThrow();


        news.setTitle(title);
        news.setAuthor(author);
        news.setBody_text(body_text);
        news.setViews(views);
        news.setLikes(likes);

        newsRepository.save(news);
        return "redirect:/News/";
    }

}
