package com.kristiania.exam.services;

import com.kristiania.exam.TestApplication;
import com.kristiania.exam.backend.entity.Copy;
import com.kristiania.exam.backend.entity.Users;
import com.kristiania.exam.backend.service.CopyService;
import com.kristiania.exam.backend.service.ItemService;
import com.kristiania.exam.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class PurchaseServiceTest extends ServiceTestBase{
    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreatePurchase() {
        userService.createUser("Arne","Arne", "Super" ,"123","a@gmail.com", "user");
        Long itemId = itemService.createItem("Eevium", "Allows Eevee to upgrade Last Resort to a Z-Move, Extreme Evoboost.", 15L);
        Long purchaseVirtualItemID = copyService.purchaseVirtualPockemonItem(itemId,"Arne");
        assertNotNull(purchaseVirtualItemID);
    }
    @Test
    public void testFilterPurchasesByUser() {
        String userName = "Arne";
        //For simplicity username and name are same
        userService.createUser(userName, userName, "Super" ,"123",userName+"@gmail.com", "user");
        Long firstItem = itemService.createItem("Dubious Disc", "Evolves Porygon2 when traded holding the item.", 12L);
        Long secondItem = itemService.createItem("Durin Berry", "A Berry which is very rare in the Unova region. A maniac will buy it for a high price.", 12L);
        Long firstPurchase = copyService.purchaseVirtualPockemonItem(firstItem, userName);
        Long secondPurchase = copyService.purchaseVirtualPockemonItem(secondItem, userName);
        Users users = userService.findUserByUserName(userName);
        assertNotNull(firstPurchase);
        assertNotNull(secondPurchase);
        List<Copy> userPurchases = copyService.filterPurchasesByUser(users.getUserID());
        assertEquals(2, userPurchases.size());
    }

    @Test
    public void testFilterPurchasesByTrip() {
        String firstUser = "Sirha";
        String secondUser = "Kefir";
        //For simplicity username and name are same
        userService.createUser(firstUser, firstUser, "Super" ,"123",firstUser+"@gmail.com", "user");
        userService.createUser(secondUser, secondUser, "Super" ,"123",secondUser+"@gmail.com", "user");
        Long firstItem = itemService.createItem("Dream Ball", "A special Poké Ball that appears out of nowhere in a bag at the Entree Forest. It can catch any Pokémon.", 13L);
        Long secondItem = itemService.createItem("Darkinium", "Allows the use of Black Hole Eclipse, the Dark type Z-Move.", 13L);
        Long firstPurchase = copyService.purchaseVirtualPockemonItem(firstItem, firstUser);
        Long secondPurchase = copyService.purchaseVirtualPockemonItem(secondItem, firstUser);
        Long thirdPurchase = copyService.purchaseVirtualPockemonItem(secondItem, secondUser);
        assertNotNull(firstPurchase);
        assertNotNull(secondPurchase);
        assertNotNull(thirdPurchase);

        List<Copy> firstTripFilter = copyService.filterPurchasesByItem(firstItem);
        List<Copy> secondTripFilter = copyService.filterPurchasesByItem(secondItem);

        assertEquals(1, firstTripFilter.size());
        assertEquals(2, secondTripFilter.size());
    }

    @Test
    public void testPurchaseLootByRedeemCurrency() {
        String userName = "Flipi";
        Long lootBox = 25L;
        Long currency = 100L;
        Long lootBoxAfter = 29L;
        Long currencyAfter = 99L;
        //For simplicity username and name are same
        userService.createUser(userName, userName, "Super" ,"123",userName+"@gmail.com", "user");
        Users users = userService.findUserByUserName(userName);
        assertNotNull(users);
        assertEquals(lootBox, users.getAvailableLootBoxes());
        assertEquals(currency, users.getInGameCurrency());
        users.setInGameCurrency(users.getInGameCurrency()- 1);
        users.setAvailableLootBoxes(users.getAvailableLootBoxes() + (4 * 1));
        Users usersAfterPurchaseLoot = userService.updateUser(users);
        assertEquals(lootBoxAfter, usersAfterPurchaseLoot.getAvailableLootBoxes());
        assertEquals(currencyAfter, usersAfterPurchaseLoot.getInGameCurrency());
    }

    @Test
    public void testDeletePurchase() {
        String user = "Abram";
        userService.createUser(user,user, "Super" ,"123","a@gmail.com", "user");
        Users users = userService.findUserByUserName(user);
        Long itemId = itemService.createItem("Eevium", "Allows Eevee to upgrade Last Resort to a Z-Move, Extreme Evoboost.", 15L);
        Long purchaseVirtualItemID = copyService.purchaseVirtualPockemonItem(itemId,users.getUserID());
        Boolean result = copyService.deletePurchasesHist(purchaseVirtualItemID);
        assertNotNull(result);
        assertEquals(Boolean.TRUE, result);
    }
}
