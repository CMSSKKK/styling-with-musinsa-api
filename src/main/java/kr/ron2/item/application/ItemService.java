package kr.ron2.item.application;

import kr.ron2.brand.domain.Brand;
import kr.ron2.brand.domain.BrandRepository;
import kr.ron2.category.domain.Category;
import kr.ron2.category.domain.CategoryRepository;
import kr.ron2.event.Events;
import kr.ron2.event.ItemRemoveEvent;
import kr.ron2.event.ItemUpsertEvent;
import kr.ron2.item.domain.Item;
import kr.ron2.item.domain.ItemRepository;
import kr.ron2.item.domain.dto.LowestPriceInfo;
import kr.ron2.item.ui.dto.TotalLowestPriceOfBrand;
import kr.ron2.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public void save(Long categoryId, Long brandId, Money price) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(NoSuchElementException::new);

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(NoSuchElementException::new);
        Item item = itemRepository.save(Item.from(category, brand, price));
        Events.raise(new ItemUpsertEvent(item));
    }

    @Transactional(readOnly = true)
    public TotalLowestPriceOfBrand searchLowestPriceOfBrand(Long brandId) {
        List<LowestPriceInfo> lowestPriceInfosByBrand = itemRepository.findLowestPriceInfosByBrand(brandId);
        if (lowestPriceInfosByBrand.isEmpty()) {
            throw new NoSuchElementException();
        }
        LowestPriceInfo lowestPriceInfo = lowestPriceInfosByBrand.get(0);

        return new TotalLowestPriceOfBrand(lowestPriceInfo.getBrandName(), sum(lowestPriceInfosByBrand));
    }

    @Transactional
    public void remove(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(NoSuchElementException::new);
        Events.raise(new ItemRemoveEvent(item));
        itemRepository.delete(item);

    }

    private Integer sum(List<LowestPriceInfo> infos) {
        Money money = infos.stream()
                .map(LowestPriceInfo::getPrice)
                .reduce(Money::plus)
                .orElseThrow(RuntimeException::new);

        return money.getValue();
    }


}
