package hachi.hachishop.Controller;

import hachi.hachishop.domain.item.Book;
import hachi.hachishop.domain.item.Item;
import hachi.hachishop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * @param model 상품등록 폼에서 데이터를 입력하고 Submit 버튼을 클릭하면
     * /items/new 를 POST 방식으로 요청 상품 저장이 끝나면
     * @return 상품 목록 화면( redirect:/items )으로 리다이렉트
     */
    @GetMapping("/items/new")
    public String createForm(Model model)  {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createItemForm";
        }


        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> itemList = itemService.findItems();
        model.addAttribute("items", itemList);
        return "items/itemList";

    }



}
