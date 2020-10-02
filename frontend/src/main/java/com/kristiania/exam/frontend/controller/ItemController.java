package com.kristiania.exam.frontend.controller;

import com.kristiania.exam.backend.entity.Copy;
import com.kristiania.exam.backend.entity.Item;
import com.kristiania.exam.backend.entity.Users;
import com.kristiania.exam.backend.service.CopyService;
import com.kristiania.exam.backend.service.ItemService;
import com.kristiania.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.HttpClientErrorException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class ItemController implements Serializable {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    private Long itemID;

    public List<Copy> getUserItems(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findUserByUserName(user.getUsername());
        return copyService.filterPurchasesByUser(users.getUserID());
    }

    public List<Item> getItems(int numberOFItems) {
        return itemService.getAllItems().stream().limit(numberOFItems).collect(Collectors.toList());
    }

    public String getTripRedirectionLink(Long itemID) {
        this.itemID = itemID;
        return "/details.jsf?itemId=" + itemID + "&faces-redirect=true";
    }

    public Item getItem(Long id) {
        return itemService.getItem(id);
    }

    public List<Item> filterCopyOfItemBy(String searchBy, String query) {
        if (searchBy.equals("byCost")) {
            return itemService.filterByCost(Long.valueOf(query));
        } else if (searchBy.equals("byTitle")) {
            return itemService.getAllItemsByTitle(query);
        } else {
            return null;
        }
    }

    public String makePurchase(String userID) {
        if (isNotPurchased(itemID,userID)) {
            copyService.purchaseVirtualPockemonItem(itemID, userID);
            return "details?itemId=" + itemID + "&isPurchased=true&faces-redirect=true";
        }else{
            return "details?itemId=" + itemID + "&isPurchased=false&faces-redirect=true";
        }
    }


    public Boolean isNotPurchased(Long itemId,String userId) {
        List<Copy> allPurchase = copyService.filterPurchasesByUser(userId);
        return allPurchase.stream().filter(p -> p.getItem().getItemId().equals(itemId)).findAny().orElse(null) == null;
    }

}
