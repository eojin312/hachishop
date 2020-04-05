package hachi.hachishop.repository;

import hachi.hachishop.domain.item.Member;
import hachi.hachishop.domain.item.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;


    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //검색용 메소드
    public List<Order> findAll(OrderSearch orderSearch) {

        return em.createQuery("select o from Order o join o.member m" + " where o.status = :status" +
                " and m.name like :name" ,Order.class)
                .setParameter("status", orderSearch.getOrderStatus()) //파라미터 바인딩
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000) //최대 천건 결과 제한하기
                .getResultList();
    }
}
