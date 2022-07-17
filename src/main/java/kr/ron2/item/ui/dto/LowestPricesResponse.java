package kr.ron2.item.ui.dto;

import kr.ron2.item.domain.dto.LowestPriceInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class LowestPricesResponse {

    private final List<LowestPriceInfo> lowestPriceInfos;
    private final Integer totalSum;
}
