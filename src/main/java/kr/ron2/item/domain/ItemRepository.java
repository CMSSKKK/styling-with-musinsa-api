package kr.ron2.item.domain;

import kr.ron2.item.domain.dto.LowestPriceInfo;
import kr.ron2.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select min(i.price) from Item i where i.category = :category_id and i.brand = :brand_id")
    Money findMinPriceByCategoryAndBrand(@Param("category_id") Long categoryId, @Param("brand_id") Long brandId);

    @Query( "select new kr.ron2.item.domain.dto.LowestPriceInfo(i.category.name, i.brand.name, min(i.price)) from Item i " +
            "join Category c on c = i.category " +
            "join Brand b on b = i.brand " +
            "group by i.category, i.brand")
    List<LowestPriceInfo> searchLowestPriceInfoGroupByCategoryAndBrand();

    @Query("select new kr.ron2.item.domain.dto.LowestPriceInfo(i.category.name, i.brand.name, min(i.price)) from Item i " +
            "join Category c on c = i.category " +
            "join Brand b on b = i.brand " +
            "group by i.category, i.brand having i.brand.id = :brand")
    List<LowestPriceInfo> findLowestPriceInfosByBrand(@Param("brand") Long brandId);
}
