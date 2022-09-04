package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SampleForm2 {

    @NotBlank
    private String title;
    private List<String> items;
    private List<String> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
