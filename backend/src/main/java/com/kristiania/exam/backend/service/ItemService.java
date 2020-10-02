package com.kristiania.exam.backend.service;

import com.kristiania.exam.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    @Autowired
    private EntityManager entityManager;

    public List<Item> getAllItems() {
        TypedQuery<Item> query = entityManager.createQuery("SELECT t FROM Item t", Item.class);
        return query.getResultList();
    }

    public List<Item> getAllItemsByCost(Long cost) {
        TypedQuery<Item> query = entityManager.createQuery("SELECT t FROM Item t where t.itemCost =?1", Item.class);
        query.setParameter(1, cost);
        List<Item> itemList = query.getResultList();
        return itemList;
    }

    public List<Item> getAllItemsByTitle(String title) {
        TypedQuery<Item> query = entityManager.createQuery("SELECT t FROM Item t where t.title =?1", Item.class);
        query.setParameter(1, title);
        List<Item> itemList = query.getResultList();
        return itemList;
    }

    public Long createItem(String title, String description, Long cost) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setItemCost(cost);
        entityManager.persist(item);
        return item.getItemId();
    }

    public Item getItem(Long itemId) {
        Item item = entityManager.find(Item.class, itemId);
        if (item == null) {
            throw new IllegalStateException("No such item found");
        }
        if (item.getCopyList() !=null) {
            item.getCopyList().size();
        }
        return item;
    }

    public void deleteItem(Long itemId) {
        Item itemRemove = entityManager.find(Item.class, itemId);
        if (itemRemove == null) {
            throw new IllegalStateException("Item not found in database");
        }
        entityManager.remove(itemRemove);
    }

    public List<Item> filterByCost(Long cost) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT t FROM Item t WHERE t.itemCost =?1", Item.class);
        query.setParameter(1, cost);
        return query.getResultList();
    }
}
