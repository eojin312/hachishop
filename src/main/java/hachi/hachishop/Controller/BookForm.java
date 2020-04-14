package hachi.hachishop.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    private Long id;


    //상품의 공통속성
    private String name;

    private int price;

    private int stockQuantity;

    //책과 관려된 특별한 속성
    private String author;
    private String isbn;
}
