package kr.ron2.search.application;

import kr.ron2.item.domain.Item;
import kr.ron2.category.domain.Category;
import kr.ron2.search.domain.PriceInfo;
import kr.ron2.search.domain.PriceInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceInfoService {

    private final PriceInfoRepository priceInfoRepository;

    public void update(Item item) {
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

}
