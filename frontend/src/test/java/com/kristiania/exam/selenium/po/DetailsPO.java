package com.kristiania.exam.selenium.po;

import com.kristiania.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DetailsPO extends LayoutPO {
    public DetailsPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public DetailsPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Item Details");
    }

    public DetailsPO makePurchase(String userID){
        //If we are not logged in we can't buy a loot
        if(getDriver().findElements(By.id("purchaseBtn")).size() == 0)
            return null;
        clickAndWait("purchaseBtn");
        DetailsPO detailsPO = new DetailsPO(this);
        //After clicking booking button our table should have id of user in it
        //User id is found first column

        assertTrue(isInFirstColumn(userID));
        return detailsPO;
    }
}
