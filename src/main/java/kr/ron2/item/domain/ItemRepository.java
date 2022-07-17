package kr.ron2.item.domain;

import kr.ron2.item.domain.dto.LowestPriceInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findFirstByCategoryIdOrderByPriceAsc(@Param("category_id") Long categoryId);

    Optional<Item> findFirstByCategoryIdOrderByPriceDesc(@Param("category_id") Long categoryId);

    @Query("select new kr.ron2.item.domain.dto.LowestPriceInfo(i.category.name, i.brand.name, min(i.price)) from Item i " +
            "join Category c on c = i.category " +
            "join Brand b on b = i.brand " +
            "group by i.category, i.brand having i.brand.id = :brand")
    List<LowestPriceInfo> findLowestPriceInfosByBrand(@Param("brand") Long brandId);


}
