package kr.ron2.search.domain;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PriceInfoRepository extends JpaRepository<PriceInfo, Long> {

    List<PriceInfo> findAllByStatistics(Statistics statistics);

    List<PriceInfo> findAllByCategoryId(Long categoryId);

}
