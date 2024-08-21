package MR.Movie.Review.Controller;

import MR.Movie.Review.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("login")
    public String login(@RequestParam(value = "id") String id,
                        @RequestParam(value = "password") String password,
                        Model model) {
        model.addAttribute("loginId", id);
        model.addAttribute("loginPassword", password);
        return "homepage";
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }

    @PostMapping("join")
    public String joined(@RequestParam(value = "name") String name,
                         @RequestParam(value = "id") String id,
                         @RequestParam(value = "password") String password,
                         Model model) {
        model.addAttribute("joinName", name);
        model.addAttribute("joinId", id);
        model.addAttribute("joinPassword", password);
        return "joined";
    }

}
