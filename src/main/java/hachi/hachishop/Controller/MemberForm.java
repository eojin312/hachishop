package hachi.hachishop.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 화면 요구사항이 복잡해지기 시작하면, 엔티티에 화면을 처리하기 위한 기능이 점점 증가한다.
 * 결과적으로 엔티티는 점점 화면에 종속적으로 변하고,
 * 이렇게 화면 기능 때문에 지저분해진 엔티티는 결국 유지보수하기 어려워진다.
 * 실무에서 엔티티는 핵심 비즈니스 로직만 가지고 있고, 화면을 위한 로직은 없어야 한다
 */

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원이름은 필수 입니다")
    private String name;

    @NotEmpty(message = "도시를 꼭 넣어주세요")
    private String city;

    @NotEmpty(message = "주소를 꼭 넣어주세요")
    private String street;

    @NotEmpty(message = "우편번호는 필수 입니다")
    private String zipcode;

}
