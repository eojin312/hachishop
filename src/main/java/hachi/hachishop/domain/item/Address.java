package hachi.hachishop.domain.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //public 으로 하면 많은 사람들이 사용할 수 있기 때문에 jpa 스펙에 따라 만듬
    protected Address() {
    }

    //생성자에서 값을 모두 초기화해서 변경 불가능한 클래스로 만들기
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
