package com.example.duan_oder_doan.model;

public class TheLoai {
    private int img_category;
    private String name_category;

    public TheLoai() {
    }

    public TheLoai(int img_category, String name_category) {
        this.img_category = img_category;
        this.name_category = name_category;
    }

    public int getImg_category() {
        return img_category;
    }

    public void setImg_category(int img_category) {
        this.img_category = img_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }
}
