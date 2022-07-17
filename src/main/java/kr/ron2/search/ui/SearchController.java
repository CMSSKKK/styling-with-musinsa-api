package kr.ron2.search.ui;

import kr.ron2.item.application.ItemService;
import kr.ron2.search.ui.dto.MaxAndMinSimpleData;
import kr.ron2.search.ui.dto.TotalLowestPriceOfBrand;
import kr.ron2.search.application.PriceInfoService;
import kr.ron2.search.domain.Statistics;
import kr.ron2.search.ui.dto.ItemSimpleData;
import kr.ron2.search.ui.dto.PriceInfosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final ItemService itemService;
    private final PriceInfoService priceInfoService;

    /*
     * (필수) 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회
     * */
    @GetMapping("/min")
    public PriceInfosResponse searchTotalMin() {
        return priceInfoService.findLowestPrices();
    }

    /*
     * (필수) 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
     * */
    @GetMapping("/brands/{id}")
    public TotalLowestPriceOfBrand searchBrandMinTotalPrice(@PathVariable Long id) {
        return itemService.searchLowestPriceOfBrand(id);
    }

    /*
    * (필수) 각 카테고리 이름으로 최소, 최대 가격 조회 API
    * */
    @GetMapping("/categories/{categoryId}")
    public MaxAndMinSimpleData searchMaxAndMinData(@PathVariable Long categoryId) {
        return priceInfoService.findSimpleDataByCategory(categoryId);
    }

}
