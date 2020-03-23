package hachi.hachishop.domain.item;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address adress;

    /**
     * 1:N
     * 서로 반대
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
