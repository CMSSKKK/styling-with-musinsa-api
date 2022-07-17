package kr.ron2.search.application;

import kr.ron2.category.domain.Category;
import kr.ron2.item.domain.Item;
import kr.ron2.item.domain.ItemRepository;
import kr.ron2.model.Money;
import kr.ron2.search.domain.PriceInfo;
import kr.ron2.search.domain.PriceInfoRepository;
import kr.ron2.search.domain.Statistics;
import kr.ron2.search.ui.dto.ItemSimpleData;
import kr.ron2.search.ui.dto.PriceInfoDto;
import kr.ron2.search.ui.dto.PriceInfosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceInfoService {

    private final PriceInfoRepository priceInfoRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void updateWhenUpsertItem(Item item) {
        Category category = item.getCategory();

        List<PriceInfo> priceInfos = priceInfoRepository.findAllByCategoryId(category.getId());

        if (priceInfos.isEmpty()) {
            throw new RuntimeException();
        }

        for (PriceInfo priceInfo : priceInfos) {
            if (priceInfo.hasToUpdateMin(item)) {
                priceInfo.update(item);
            }
            if(priceInfo.hasToUpdateMax(item)) {
                priceInfo.update(item);
            }
        }
    }

    @Transactional
    public void updateWhenDeleteItem(Item item) {
        Category category = item.getCategory();
        List<PriceInfo> priceInfos = priceInfoRepository.findAllByCategoryId(category.getId());

        if (priceInfos.isEmpty()) {
            throw new RuntimeException();
        }

        Item maxItem = itemRepository.findMaxByCategory(category.getId())
                .orElseThrow(NoSuchElementException::new);

        Item minItem = itemRepository.findMinByCategory(category.getId())
                .orElseThrow(NoSuchElementException::new);

        for (PriceInfo priceInfo : priceInfos) {
            if (priceInfo.hasToUpdateMin(minItem)) {
                priceInfo.update(minItem);
            }

            if(priceInfo.hasToUpdateMax(maxItem)) {
                priceInfo.update(maxItem);
            }
        }

    }

    @Transactional(readOnly = true)
    public PriceInfosResponse findLowestPrices() {
        List<PriceInfo> priceInfos = priceInfoRepository.findAllByStatistics(Statistics.MIN);
        List<PriceInfoDto> infoDtos = priceInfos.stream()
                .map(PriceInfoDto::of)
                .collect(Collectors.toList());

        return new PriceInfosResponse(infoDtos, totalSum(infoDtos));
    }

    @Transactional(readOnly = true)
    public ItemSimpleData findSimpleDataByCategory(Long categoryId, Statistics statistics) {
        PriceInfo priceInfo = priceInfoRepository.findPriceInfoByCategoryIdAndStatistics(categoryId, statistics);
        return ItemSimpleData.of(priceInfo);
    }

    private Integer totalSum(List<PriceInfoDto> infoDtos) {
        Money money = infoDtos.stream()
                .map(PriceInfoDto::getPrice)
                .reduce(Money::plus)
                .orElseThrow(RuntimeException::new);

        return money.getValue();
    }



}
