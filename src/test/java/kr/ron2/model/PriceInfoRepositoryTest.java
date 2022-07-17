package kr.ron2.model;

import kr.ron2.search.domain.PriceInfo;
import kr.ron2.search.domain.PriceInfoRepository;
import kr.ron2.search.domain.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class PriceInfoRepositoryTest {

    @Autowired
    private PriceInfoRepository priceInfoRepository;

    @Test
    void findAllByStatistics() {

        List<PriceInfo> allByStatistics = priceInfoRepository.findAllByStatistics(Statistics.MIN);
        System.out.println(allByStatistics.size());

    }
}
