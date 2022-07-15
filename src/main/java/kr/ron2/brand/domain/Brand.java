package kr.ron2.brand.domain;

import kr.ron2.Item.domain.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    private boolean isDeleted;
}
