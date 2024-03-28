package hello.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    // GetMapping /hello 되어있는 페이지에서 아래의 매서드을 실행함
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");

        // return을 하는 것은 hello.html파일을 찾아감
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

}
