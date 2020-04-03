package hachi.hachishop.repository;

import hachi.hachishop.domain.item.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; //주문 상태
}
