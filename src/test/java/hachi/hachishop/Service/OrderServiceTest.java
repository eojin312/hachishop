package hachi.hachishop.Service;

import hachi.hachishop.domain.item.*;
import hachi.hachishop.repository.OrderRepository;
import hachi.hachishop.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        Member member = new Member();

        member.setName("회원1");
        member.setAdress(new Address("서울", "경기", "123-213"));
        em.persist(member);

        Item item = new Book();

        Book book = new Book();
        book.setName("JPA 책");
        book.setPrice(10000);
        book.setStockQuantity(10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());

    }

    @Test
    public void 주문취소() {
    }

    @Test
    public void 재고수량초과() {

    }
}