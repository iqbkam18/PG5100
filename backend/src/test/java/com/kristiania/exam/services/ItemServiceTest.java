package com.kristiania.exam.services;

import com.kristiania.exam.TestApplication;
import com.kristiania.exam.backend.entity.Item;
import com.kristiania.exam.backend.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest extends ServiceTestBase {

    @Autowired
    private ItemService itemService;

    @Test
    public void testCreateItem() {
        Long itemId = itemService.createItem(
                "Adrenaline Orb",
                "Using it makes wild Pokémon more likely to call for help. If held by a Pokémon, it boosts Speed when intimidated. It can be used only once.",
                2L);
        assertNotNull(itemId);
        Item item = itemService.getItem(itemId);
        assertNotNull(item);
    }

    @Test
    public void testGetAllItems() {
        Long itemCreateOne = itemService.createItem(
                "Aguav Berry",
                "Restores HP if it's low, but may cause confusion.",
                5L);
        Long itemCreateTwo = itemService.createItem(
                "Altarianite",
                "Enables Altaria to Mega Evolve during battle.",
                5L);
        assertNotNull(itemCreateOne);
        assertNotNull(itemCreateTwo);
        List<Item> allItems = itemService.getAllItemsByCost(5L);
        assertEquals(2, allItems.size());
    }

    @Test
    public void testDeleteItem() {
        Long itemId = itemService.createItem(
                "Ampharosite",
                "Enables Ampharos to Mega Evolve during battle.",
                3L
        );
        //Check that it is in database
        assertNotNull(itemId);
        itemService.deleteItem(itemId);
        //Should not find trip with this id in database since we just deleted it
        assertThrows(IllegalStateException.class, () -> itemService.getItem(itemId));
    }

    @Test
    public void testFilterTripsByTitle() {
        Long itemOne = itemService.createItem(
                "Elixir",
                "It restores the PP of all the moves learned by the targeted Pokémon by 10 points each.",
                6L
        );
        Long itemTwo = itemService.createItem(
                "Energy",
                "Restores 60 HP but lowers Friendship.",
                6L
        );
        Long itemThree = itemService.createItem(
                "Energy",
                "f held by a Pokémon, it restores its HP if it is hit by any supereffective attack.",
                6L
        );
        assertNotNull(itemOne);
        assertNotNull(itemTwo);
        assertNotNull(itemThree);

        List<Item> itemsOne = itemService.getAllItemsByTitle("Elixir");
        List<Item> itemsTwo = itemService.getAllItemsByTitle("Energy");

        assertEquals(1, itemsOne.size());
        assertEquals(2, itemsTwo.size());
    }

    @Test
    public void filterByCost() {
        Long itemCreateOne = itemService.createItem(
                "Aguav Berry",
                "Restores HP if it's low, but may cause confusion.",
                10L);
        Long itemCreateTwo = itemService.createItem(
                "Altarianite",
                "Enables Altaria to Mega Evolve during battle.",
                20L);
        Long itemCreateThree = itemService.createItem(
                "Aguav Berry",
                "Restores HP if it's low, but may cause confusion.",
                20L);
        assertNotNull(itemCreateOne);
        assertNotNull(itemCreateTwo);
        assertNotNull(itemCreateThree);

        List<Item> cheapItem = itemService.filterByCost(10L);
        List<Item> expensiveItems = itemService.filterByCost(20L);

        assertEquals(1, cheapItem.size());
        assertEquals(2, expensiveItems.size());
    }


}
