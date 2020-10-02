package com.kristiania.exam.services;

import com.kristiania.exam.TestApplication;
import com.kristiania.exam.backend.service.CopyService;
import com.kristiania.exam.backend.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/**
 * This class is adaptation of:
 * https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {

    @Autowired
    private CopyService copyService;

    @Autowired
    private ItemService itemService;

    @Test
    public void testInit() {
        assertTrue(copyService.getAllCopys().size() > 0);
        assertTrue(itemService.getAllItems().size() > 0);
//        assertTrue(itemService.getAllItems().get(0).getCopyList().size() > 0);
    }
}