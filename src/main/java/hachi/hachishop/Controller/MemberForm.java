package hachi.hachishop.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

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
