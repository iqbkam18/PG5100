package com.kristiania.exam.frontend.controller;

import com.kristiania.exam.backend.entity.Copy;
import com.kristiania.exam.backend.entity.Users;
import com.kristiania.exam.backend.service.CopyService;
import com.kristiania.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

/**
 * This class is adaptation of:
 * https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/controller/UserInfoController.java
 */
@Named
@RequestScoped
public class UserInfoController {

    @Autowired
    private CopyService purchaseService;

    @Autowired
    private UserService userService;

    Long inGameCurrency;

    public UserInfoController() {
    }

    public String getUserName() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Long getLootBoxes(){
        Users users = getUser();
        if(null == users){
            return 0L;
        }else{
            return users.getAvailableLootBoxes();
        }
    }

    public Long getGameCurrency(){
        Users users = getUser();
        if(null == users){
            return 0L;
        }else{
            return users.getInGameCurrency();
        }
    }

    public String purchaseLoot(){
        Users users = getUser();
        Boolean aBoolean = userService.purchaseLootByRedeemCurrency(users,inGameCurrency);
        if(aBoolean){
            return "/index.jsf?faces-redirect=true";
        }else{
            return "/buy_loot.jsf?faces-redirect=true";
        }
    }


    public Users getUser(){
        return userService.findUserByUserName(getUserName());
    }

    public List<Copy> listPurchases() {
        return purchaseService.filterPurchasesByUser(getUserName());
    }

    public Long getInGameCurrency() {
        return inGameCurrency;
    }

    public void setInGameCurrency(Long inGameCurrency) {
        this.inGameCurrency = inGameCurrency;
    }
}
