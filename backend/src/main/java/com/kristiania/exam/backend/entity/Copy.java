package com.kristiania.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Copy implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotNull
    private Long lootBoxes;

    @NotNull
    private Long itemCost;

    @NotNull
    private LocalDate buyDate;

    public Long getId() {
        return id;
    }

    public Copy setId(Long id) {
        this.id = id;
        return this;
    }

    public Users getUsers() {
        return users;
    }

    public Copy setUsers(Users users) {
        this.users = users;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Copy setItem(Item item) {
        this.item = item;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Copy setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Copy setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getLootBoxes() {
        return lootBoxes;
    }

    public Copy setLootBoxes(Long lootBoxes) {
        this.lootBoxes = lootBoxes;
        return this;
    }

    public Long getItemCost() {
        return itemCost;
    }

    public Copy setItemCost(Long itemCost) {
        this.itemCost = itemCost;
        return this;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public Copy setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
        return this;
    }
}
