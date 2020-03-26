package hachi.hachishop.Service;

import hachi.hachishop.domain.item.Member;
import hachi.hachishop.repository.MemberRepository;
import hachi.hachishop.service.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given (정보를 주고)
        Member member = new Member();
        member.setName("Hachi");

        //when(~~할때)
        Long saveId = memberService.join(member);

        //then(이렇게 결과가 나와야함)
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 중복회원테스트() throws Exception{
        Member member1 = new Member();
        member1.setName("hachi");

        Member member2 = new Member();
        member2.setName("huchu");

        memberService.join(member1);
        memberService.join(member2);

        Assert.fail("예외가 발생해야한다");
    }
}
