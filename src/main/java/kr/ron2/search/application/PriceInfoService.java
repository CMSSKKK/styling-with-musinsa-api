package kr.ron2.search.application;

import kr.ron2.category.domain.Category;
import kr.ron2.item.domain.Item;
import kr.ron2.item.domain.ItemRepository;
import kr.ron2.common.model.Money;
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
            // TODO 카테고리 생성 로직 필요
            throw new NoSuchElementException("카테고리 생성이 필요합니다.");
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
        PriceInfo minPriceInfo = priceInfoRepository
                .findPriceInfoByCategoryIdAndStatistics(category.getId(), Statistics.MIN);
        PriceInfo maxPriceInfo = priceInfoRepository
                .findPriceInfoByCategoryIdAndStatistics(category.getId(), Statistics.MAX);
        Item minItem = itemRepository.findFirstByCategoryIdOrderByPriceDesc(category.getId())
                .orElseThrow(()-> new NoSuchElementException("카테고리 정보가 잘못되었습니다."));
        Item maxItem = itemRepository.findFirstByCategoryIdOrderByPriceAsc(category.getId())
                .orElseThrow(()-> new NoSuchElementException("카테고리 정보가 잘못되었습니다."));
        minPriceInfo.update(minItem);
        maxPriceInfo.update(maxItem);
    }

    @Transactional(readOnly = true)
    public PriceInfosResponse findLowestPrices() {
        List<PriceInfo> priceInfos = priceInfoRepository.findAllByStatistics(Statistics.MIN);
        List<PriceInfoDto> infoDtos = priceInfos.stream()
                .map(PriceInfoDto::of)
                .collect(Collectors.toList());

        return new PriceInfosResponse(infoDtos, totalSum(priceInfos));
    }

    @Transactional(readOnly = true)
    public ItemSimpleData findSimpleDataByCategory(Long categoryId, Statistics statistics) {
        PriceInfo priceInfo = priceInfoRepository.findPriceInfoByCategoryIdAndStatistics(categoryId, statistics);
        return ItemSimpleData.of(priceInfo);
    }

    private Integer totalSum(List<PriceInfo> priceInfos) {
        Money money = priceInfos.stream()
                .map(PriceInfo::getPrice)
                .reduce(Money::plus)
                .orElseThrow(RuntimeException::new);

        return money.getValue();
    }



}
