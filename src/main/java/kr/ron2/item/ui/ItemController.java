package kr.ron2.item.ui;

import kr.ron2.item.application.ItemService;
import kr.ron2.item.ui.dto.ItemRegisterRequest;
import kr.ron2.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewItem(@RequestBody ItemRegisterRequest itemRegisterRequest) {
        itemService.save(itemRegisterRequest.getCategoryId(),
                itemRegisterRequest.getBrandId(),
                new Money(itemRegisterRequest.getPrice()));
    }




}
