package ru.job4j.pojo;

public class Book {
    private String name;
    private int listCount;

    public Book(String name, int listCount) {
        this.name = name;
        this.listCount = listCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }
}
