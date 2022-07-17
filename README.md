# 옷 가격 조회 API

### Postman API 문서

- https://documenter.getpostman.com/view/16400967/UzQvt5QC#115525c2-0829-440a-9308-9be8ec193bc9)


### 기술 스택

- Java 11
- Gradle
- Spring Boot 2.6.9
- H2 dataBase
- Spring Data JPA

### 구현 고려 사항 

- 최저가 및 최저가를 조회할 때 통계 테이블(`priceInfo``을 활용하는 방법으로 접근했습니다.
- 상품이 추가, 수정, 삭제 될 시마다 `Event`를 활용해서 `priceInfo`를 업데이트하도록 로직을 구성했습니다.

```java
@Entity
public class PriceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    @Enumerated(EnumType.STRING)
    private Statistics statistics;

    private Long itemId;

    @Convert(converter = MoneyConverter.class)
    private Money price;
    
```

```java
@Service
public class PriceInfoUpdateEventHandler {

    private final PriceInfoService priceInfoService;

    @EventListener(ItemUpsertEvent.class)
    public void handleItemUpsertEvent(ItemUpsertEvent event) {
        priceInfoService.updateWhenUpsertItem(event.getItem());
    }

    @EventListener(ItemRemoveEvent.class)
    public void handleItemRemoveEvent(ItemRemoveEvent event) {
        priceInfoService.updateWhenDeleteItem(event.getItem());
    }

}
```
