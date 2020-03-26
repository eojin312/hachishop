package hachi.hachishop.Controller;

import hachi.hachishop.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private MemberService memberService;



    @GetMapping("/hello")
    public String hello (Model model) {
        log.info("hello 컨트롤러 진입");
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}