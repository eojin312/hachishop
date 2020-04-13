package hachi.hachishop.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    private Long id;


    //상품의 공통속성
    @NotEmpty(message = "상품이름은 필수 입니다")
    private String name;
    @NotEmpty(message = "상품가격은 필수 입니다")
    private int price;
    @NotEmpty(message = "상품수량은 필수 입니다")
    private int stockQuantity;

    //책과 관려된 특별한 속성
    @NotEmpty(message = "책 저자는 필수 입니다")
    private String author;
    @NotEmpty(message = "ISBN 은 필수 입니다")
    private String isbn;
}
