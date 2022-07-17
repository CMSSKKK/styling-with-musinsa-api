package kr.ron2.search.ui;

import kr.ron2.item.application.ItemService;
import kr.ron2.item.ui.dto.TotalLowestPriceOfBrand;
import kr.ron2.search.application.PriceInfoService;
import kr.ron2.search.domain.Statistics;
import kr.ron2.search.ui.dto.ItemSimpleData;
import kr.ron2.search.ui.dto.PriceInfosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final ItemService itemService;
    private final PriceInfoService priceInfoService;

    @GetMapping("/brands/min")
    public TotalLowestPriceOfBrand searchBrandMinTotalPrice(Long id) {
        return itemService.searchLowestPriceOfBrand(id);
    }

    @GetMapping("/categories")
    public ItemSimpleData searchMinOrMaxItem(Long categoryId, Statistics statistics) {
        return priceInfoService.findSimpleDataByCategory(categoryId, statistics);
    }

    @GetMapping("/min")
    public PriceInfosResponse searchTotalMin() {
        return priceInfoService.findLowestPrices();
    }

}
