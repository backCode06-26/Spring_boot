package hello.helloSpring.controller;

import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
* @Controller Spring을 처음 시작할떄는 Spring컨터이너가 생기는데
* 그 안에 MemberController라는 객체을 만들고 Spring이 관리을 한다.
* (스프링 빈이라고 한다.)
*/

@Controller
public class MemberController {
    private final MemberService memberService;


    // 의존성 주입(Dependency Injection)
    @Autowired
    // 파라미터에 값을 스프링 컨테이너에 있는 값으로 연결을 해준다.
    // 아래와 같이 객체을 주입하는 이유는
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
//    위와 같은 방법으로는 스프링 컨테이너에 MemberService가 있는지 알수 없다.
}
