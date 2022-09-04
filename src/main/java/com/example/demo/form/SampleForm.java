package com.example.demo.form;

import javax.validation.constraints.NotBlank;

public class SampleForm {

    @NotBlank
    private String title;
    private String[] items;
    private String[] categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
