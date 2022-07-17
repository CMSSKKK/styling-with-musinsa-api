package kr.ron2.item.application;

import kr.ron2.item.domain.ItemRepository;
import kr.ron2.item.domain.dto.LowestPriceInfo;
import kr.ron2.item.ui.dto.LowestPricesResponse;
import kr.ron2.item.ui.dto.TotalLowestPriceOfBrand;
import kr.ron2.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public LowestPricesResponse lowestPriceInfos() {
        List<LowestPriceInfo> lowestPriceInfos = itemRepository.searchLowestPriceInfoGroupByCategoryAndBrand();
        Map<String, List<LowestPriceInfo>> groupedByCategory = groupingByCategory(lowestPriceInfos);
        List<LowestPriceInfo> extractedInfos= extractPriceInfos(groupedByCategory);

        return new LowestPricesResponse(extractedInfos, sum(extractedInfos));
    }

    public TotalLowestPriceOfBrand searchLowestPriceOfBrand(Long brandId) {
        List<LowestPriceInfo> lowestPriceInfosByBrand = itemRepository.findLowestPriceInfosByBrand(brandId);
        LowestPriceInfo lowestPriceInfo = lowestPriceInfosByBrand.get(0);

        return new TotalLowestPriceOfBrand(lowestPriceInfo.getBrandName(), sum(lowestPriceInfosByBrand));
    }

    private Map<String, List<LowestPriceInfo>> groupingByCategory(List<LowestPriceInfo> infos) {
        return infos.stream()
                .collect(Collectors.groupingByConcurrent(LowestPriceInfo::getCategoryName));
    }

    private List<LowestPriceInfo> extractPriceInfos(Map<String, List<LowestPriceInfo>> groupingInfos) {

        return groupingInfos.keySet()
                .stream()
                .map(groupingInfos::get)
                .map(value -> value.stream()
                        .min(Comparator.comparing(LowestPriceInfo::priceToInteger))
                        .orElseThrow(RuntimeException::new))
                .collect(Collectors.toList());
    }

    private Integer sum(List<LowestPriceInfo> infos) {
        Money money = infos.stream()
                .map(LowestPriceInfo::getPrice)
                .reduce(Money::plus)
                .orElseThrow(RuntimeException::new);

        return money.getValue();
    }


}
