package kr.ron2.search.ui.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MaxAndMinSimpleData {

    private final ItemSimpleData MaxBrand;
    private final ItemSimpleData minBrand;

}
