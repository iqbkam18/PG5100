package com.kristiania.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

//This class is adaptation of:
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java
@Service
public class DefaultDataInitializerService {
    @Autowired
    private UserService userService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private ItemService itemService;

    @PostConstruct
    public void init() {
        //Create some users so that we can make purchases (with same username and name)
        String firstUser = "admin";
        String secondUser = "foo";
        String thirdUser = "bar";

        attempt(() -> {
            return userService.createUser(
                    firstUser, firstUser, "admin-last-name", "123", "admin@email.com", "admin");
        });
        attempt(() -> {
            return userService.createUser(
                    secondUser, secondUser, "foo-last-name", "123", "foo@email.com", "user");
        });
        attempt(() -> {
            return userService.createUser(
                    thirdUser, thirdUser, "bar-last-name", "123", "bar@email.com", "user");
        });

        //Create some item where user need to purchase
        Long abilityCapsule = attempt(() ->
                itemService.createItem(
                        "Ability Capsule",
                        "A capsule that allows a Pokémon with two Abilities to switch between these Abilities when it is used.",
                        2L));
        Long abilityUrge = attempt(() ->
                itemService.createItem(
                        "Ability Urge",
                        "When used, it activates the Ability of an ally Pokémon.",
                        2L));
        Long abomasite = attempt(() ->
                itemService.createItem(
                        "Abomasite",
                        "Enables Abomasnow to Mega Evolve during battle.",
                        2L));
        Long absolite = attempt(() ->
                itemService.createItem(
                        "Absolite",
                        "Enables Absol to Mega Evolve during battle.",
                        2L));
        Long absorbBulb = attempt(() ->
                itemService.createItem(
                        "Absorb Bulb",
                        "A consumable bulb. If the holder is hit by a Water-type move, its Sp. Atk will rise.",
                        2L));
        Long adamantMint = attempt(() ->
                itemService.createItem(
                        "Adamant Mint",
                        "Changes the Pokémon's stats to match the Adamant nature.",
                        2L));
        Long adamantOrb = attempt(() ->
                itemService.createItem(
                        "Adamant Orb",
                        "Increases the power of Dragon- and Steel-type moves when held by Dialga.",
                        2L));
        Long adrenalineOrb = attempt(() ->
                itemService.createItem(
                        "Adrenaline Orb",
                        "Using it makes wild Pokémon more likely to call for help. If held by a Pokémon, it boosts Speed when intimidated. It can be used only once.",
                        2L));
        Long aerodactylite = attempt(() ->
                itemService.createItem(
                        "Aerodactylite",
                        "Enables Aerodactyl to Mega Evolve during battle.",
                        2L));
        Long aggronite = attempt(() ->
                itemService.createItem(
                        "Aggronite",
                        "Enables Aggron to Mega Evolve during battle.",
                        2L));

//        //Okay, lets start purchasing
        copyService.purchaseVirtualPockemonItem(abilityCapsule,firstUser);
        copyService.purchaseVirtualPockemonItem(abilityUrge,thirdUser);
        copyService.purchaseVirtualPockemonItem(abomasite,secondUser);
        copyService.purchaseVirtualPockemonItem(absolite,thirdUser);
        copyService.purchaseVirtualPockemonItem(absorbBulb,firstUser);
        copyService.purchaseVirtualPockemonItem(adamantOrb,secondUser);
    }

    private <T> T attempt(Supplier<T> lambda) {
            try {
                return lambda.get();
            } catch (Exception e) {
                return null;
            }
        }
}
