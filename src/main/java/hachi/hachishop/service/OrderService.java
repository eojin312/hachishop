package hachi.hachishop.service;


import hachi.hachishop.domain.item.*;
import hachi.hachishop.repository.ItemRepository;
import hachi.hachishop.repository.MemberRepository;
import hachi.hachishop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member =  memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAdress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);


        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }


    /**
     * 주문 취소
     */

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);

        //주문취소 jpa 더티 체킹 (변경된 부분을 update)
        order.cancel();

    }

}
