package com.kristiania.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue
    private Long itemId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Long itemCost;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copy> copyList;


    public Long getItemId() {
        return itemId;
    }

    public Item setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getItemCost() {
        return itemCost;
    }

    public Item setItemCost(Long itemCost) {
        this.itemCost = itemCost;
        return this;
    }

    public List<Copy> getCopyList() {
        return copyList;
    }

    public Item setCopyList(List<Copy> copyList) {
        this.copyList = copyList;
        return this;
    }
}
