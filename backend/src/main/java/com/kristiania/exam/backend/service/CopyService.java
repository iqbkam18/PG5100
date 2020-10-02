package com.kristiania.exam.backend.service;

import com.kristiania.exam.backend.entity.Copy;
import com.kristiania.exam.backend.entity.Item;
import com.kristiania.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CopyService {

    @Autowired
    private EntityManager entityManager;

    public List<Copy> getAllCopys() {
        TypedQuery<Copy> query = entityManager.createQuery("SELECT p FROM Copy p", Copy.class);
        return query.getResultList();
    }

    public List<Copy> filterPurchasesByItem(Long itemId) {
        TypedQuery<Copy> query = entityManager.createQuery(
                "SELECT p FROM Copy p WHERE p.item.itemId =?1", Copy.class);
        query.setParameter(1, itemId);
        //It could be that here are no purchases but that is fine
        return query.getResultList();
    }

    public Long purchaseVirtualPockemonItem(Long itemId, String userID) {
        Item item = entityManager.find(Item.class, itemId);
        Users users = entityManager.find(Users.class, userID);
        if (item == null) {
            throw new RuntimeException("Item Not Found or in-game currency 0 or loot-boxes is 0");
        }
        if (users == null || users.getInGameCurrency() == null || users.getInGameCurrency() == 0) {
            throw new RuntimeException("User not found or you have not suffociant currency");
        }

        Copy copy = new Copy();
        copy.setItem(item);
        copy.setUsers(users);
        copy.setTitle(item.getTitle());
        copy.setDescription(item.getDescription());
        copy.setItemCost(2L);
        copy.setLootBoxes(1L);
        copy.setBuyDate(LocalDate.now());
        entityManager.persist(copy);
        //update user loot-boxes and cost for item purchase; the loot and cost will be redeemed
        //For Every purchase loot boxes will be deducted 1 and currency will be deducted 2
        users.setAvailableLootBoxes(users.getAvailableLootBoxes() - 1);
        users.setInGameCurrency(users.getInGameCurrency() - 2);
        entityManager.persist(users);
        return copy.getId();
    }

    public List<Copy> filterPurchasesByUser(String userID) {
        TypedQuery<Copy> query = entityManager.createQuery(
                "SELECT p FROM Copy p WHERE p.users.userID =?1", Copy.class
        );
        query.setParameter(1, userID);
        return query.getResultList();
    }

    public Boolean deletePurchasesHist(Long copyId) {
        try {
            Copy copy = entityManager.find(Copy.class, copyId);
            entityManager.detach(copy);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
