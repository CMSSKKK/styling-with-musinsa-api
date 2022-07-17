package kr.ron2.item.ui;

import kr.ron2.item.application.ItemService;
import kr.ron2.item.ui.dto.ItemRegisterRequest;
import kr.ron2.common.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /*
    * (선택) 아이템 가격 추가
    * */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewItem(@RequestBody ItemRegisterRequest itemRegisterRequest) {
        itemService.save(itemRegisterRequest.getCategoryId(),
                itemRegisterRequest.getBrandId(),
                new Money(itemRegisterRequest.getPrice()));
    }

    /*
     * (선택) 아이템 가격 업데이트
     * */
    @PostMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody Integer price) {
        itemService.update(id, price);
    }

    /*
     * (선택) 아이템 삭제
     * */
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.remove(id);
    }




}
