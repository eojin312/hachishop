package hachi.hachishop.Service;

import hachi.hachishop.domain.item.*;
import hachi.hachishop.exception.NotEnoughStockException;
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
        Member member = createMember();

        Item item = new Book();

        Book book = createBook("JPA 책", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다,", 1, getOrder.getOrderItems().size());
        assertEquals("주문가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다", 8, book.getStockQuantity());
    }



    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item item = createBook("JPA 책", 10000, 10);

        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        fail("재고 수량 부족 예외가 발생해야한다");
    }

    @Test
    public void 주문취소() {
        Member member = createMember();
        Book item = createBook("JPA 책", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태느 CANCEL 이다", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다", 10, item.getStockQuantity());
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName("JPA 책");
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();

        member.setName("회원1");
        member.setAdress(new Address("서울", "경기", "123-213"));
        em.persist(member);
        return member;
    }
}