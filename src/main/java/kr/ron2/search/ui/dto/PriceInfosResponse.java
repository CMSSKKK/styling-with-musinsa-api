package kr.ron2.search.ui.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class PriceInfosResponse {

    private final List<PriceInfoDto> priceInfos;
    private final Integer totalSum;
}
