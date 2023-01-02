package com.example.e_commerce;

public class Modelrecord {
    String id;
    String image;
    String title;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String descreption;
    String category;
    String prix;
    String tel;
    String user;

    public Modelrecord(String id, String image, String title, String descreption, String category, String prix, String tel, String user) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.descreption = descreption;
        this.category = category;
        this.prix = prix;
        this.tel = tel;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Modelrecord(String id, String image, String title, String descreption, String category, String prix, String tel) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.descreption = descreption;
        this.category = category;
        this.prix = prix;
        this.tel = tel;
    }
}