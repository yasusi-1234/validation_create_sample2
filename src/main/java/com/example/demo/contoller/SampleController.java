package com.example.demo.contoller;

import com.example.demo.form.SampleForm;
import com.example.demo.form.SampleForm2;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/")
    public String getSample(@ModelAttribute SampleForm form) {
        createSampleForm(form);
        return "/index";
    }


    @PostMapping("/complete")
    public String postSample(@Validated @ModelAttribute SampleForm form,
                             BindingResult result) {

        checkItemsAndCategories(form, result);

        System.out.println("result.ObjectName: " + result.getObjectName());
        System.out.println(result.getErrorCount());

        result.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getField()));
        if(result.hasErrors()) {
            return "/index";
        }
        return "/complete";
    }

    @GetMapping("/sample")
    public String getSample2(@ModelAttribute SampleForm2 form) {
        createSampleForm2(form);
        return "/index2";
    }

    @PostMapping("/complete2")
    public String postSample2(@Validated @ModelAttribute SampleForm2 form,
                             BindingResult result) {

        checkItemsAndCategories2(form, result);

        System.out.println("result.ObjectName: " + result.getObjectName());
        System.out.println(result.getErrorCount());

        result.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getField()));
        if(result.hasErrors()) {
            return "/index2";
        }
        return "/complete2";
    }

    private void checkItemsAndCategories(SampleForm form, BindingResult result) {
        String[] items = form.getItems();
        String[] categories = form.getCategories();

        for (int i = 0; i < form.getItems().length; i++) {
            if(!StringUtils.hasText(items[i]) && StringUtils.hasText(categories[i])){
                result.addError(new FieldError(result.getObjectName(),"items[" + i + "]", "項目が空"));
            }else if (StringUtils.hasText(items[i]) && !StringUtils.hasText(categories[i])){
                result.addError(new FieldError(result.getObjectName(),"categories[" + i + "]", "カテゴリーが空"));
            }
        }

        // おふざけサンプル これは一応登録される なのでバインディングリザルト側？でabcのフィールドがあるかはチェックされていない
//        result.addError(new FieldError(result.getObjectName(), "abc", "うんこ"));
    }

    private void checkItemsAndCategories2(SampleForm2 form, BindingResult result) {
        List<String> items = form.getItems();
        List<String> categories = form.getCategories();

        for (int i = 0; i < form.getItems().size(); i++) {
            if(!StringUtils.hasText(items.get(i)) && StringUtils.hasText(categories.get(i))){
                result.addError(new FieldError(result.getObjectName(),"items[" + i + "]", "項目が空"));
            }else if (StringUtils.hasText(items.get(i)) && !StringUtils.hasText(categories.get(i))){
                result.addError(new FieldError(result.getObjectName(),"categories[" + i + "]", "カテゴリーが空"));
            }
        }

        // おふざけサンプル これは一応登録される なのでバインディングリザルト側？でabcのフィールドがあるかはチェックされていない
//        result.addError(new FieldError(result.getObjectName(), "abc", "うんこ"));
    }

    private void createSampleForm(SampleForm form) {
        String title = "title";
        String[] items = {"item1", "item2", "item3"};
        String[] categories = {"category1", "category2", "category3"};
        form.setTitle(title);
        form.setItems(items);
        form.setCategories(categories);
    }

    private void createSampleForm2(SampleForm2 form) {
        String title = "title";
        String[] items = {"item1", "item2", "item3"};
        String[] categories = {"category1", "category2", "category3"};
        form.setTitle(title);
        form.setItems(Arrays.asList(items));
        form.setCategories(Arrays.asList(categories));
    }
}
