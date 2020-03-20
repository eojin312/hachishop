package hachi.hachishop.domain;

import hachi.hachishop.domain.item.Delivery;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    /**
     * N:1
     */
    @ManyToOne
    @JoinColumn(name = "member_id") //맵핑을 어디로 해주냐 member_id 가 FK 가 됨
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems= new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    private OderStatus status; //주문 상태 {ORDER 와 CANCEL} enum 이다
}
