package hello.helloSpring.controller;

import hello.helloSpring.domain.Member;
import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
* @Controller Spring을 처음 시작할떄는 Spring컨터이너가 생기는데
* 그 안에 MemberController라는 객체을 만들고 Spring이 관리을 한다.
* (스프링 빈이라고 한다.)
*/

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/creatMemberForm";
    }

    @PostMapping("/members/new") // form으로 불러온 정보을 가져온다.
    /*
    * 이 메서드의 파라미터가 MemberFrom클래스이기 떄문에 이 클래스로 값이 들어오게된다.
    * Spring이 가져오러는 값이 private이기 때문에 setter을 이용하여 값을 넣는다.
    */
    public String creat(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // member이름으로 members의 List을 가져옴
        return "members/memberList";
    }
}
