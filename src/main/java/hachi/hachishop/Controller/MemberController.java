package hachi.hachishop.Controller;

import hachi.hachishop.domain.item.Address;
import hachi.hachishop.domain.item.Member;
import hachi.hachishop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); //화면에서 이 객체 접속 가능
        return "members/createMemberForm";
    }

    //bindingresult 가 있으면 오류가 거기에 담겨서 실해오딤
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAdress(address);

        memberService.join(member);
        return "redirect:/";
    }

}
