package Practika1.Calculator.controllers;

import com.sun.jdi.connect.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "Калькулятор");
        return "Calculator"; // возвращает файл из папки resources/tempaltes/*название Httml.файла*
    }

    @PostMapping("/res")
    public String res(@RequestParam(value = "variable", required = false, defaultValue = "1") int a,
                       @RequestParam(value = "variant", required = false, defaultValue = "5")  String F,
                       @RequestParam(value = "variable2", required = false, defaultValue = "2") int b, Model model) {

        int c = 23;
        if (F.contains("1")) {
            c = a + b;
        }
        if (F.contains("2")) {
            c = a - b;
        }
        if (F.contains("3")){
            c = a * b;
        }
        if (F.contains("4")) {
            c = a / b;
        }

        model.addAttribute("answer", c);
        return "Answer";
    }
}
