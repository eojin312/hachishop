package hachi.hachishop.repository;

import hachi.hachishop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.matcher.InstanceTypeMatcher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else { //여기서 merge 가 뭐지?! 하지만 실무에서 많이 쓸 일은 없음
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
