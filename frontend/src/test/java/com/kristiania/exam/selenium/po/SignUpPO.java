package com.kristiania.exam.selenium.po;

import com.kristiania.exam.selenium.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class SignUpPO extends LayoutPO {
    public SignUpPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    public IndexPO signUP(String userID, String password){
        setText("username", userID);
        setText("password", password);
        clickAndWait("signUpBtn");

        IndexPO indexPO = new IndexPO(this);
        Assertions.assertTrue(getDriver().getTitle().contains("Home page"));

        return indexPO;
    }
}
