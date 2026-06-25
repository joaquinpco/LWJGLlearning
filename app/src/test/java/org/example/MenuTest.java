package org.example;

import org.example.game.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void defaultSelectionIsPlayTest() {
        Menu menu = new Menu();
        assertEquals("Play", menu.getSelectedOption());
        assertEquals(0, menu.getSelectedIndex());
    }

    @Test
    void selectDownAdvancesSelectionTest() {
        Menu menu = new Menu();
        menu.selectDown();
        assertEquals("Settings", menu.getSelectedOption());
        assertEquals(1, menu.getSelectedIndex());
    }

    @Test
    void selectUpWrapsAroundToExitTest() {
        Menu menu = new Menu();
        menu.selectUp();
        assertEquals("Exit", menu.getSelectedOption());
        assertEquals(2, menu.getSelectedIndex());
    }

    @Test
    void selectDownWrapsFromExitToPlayTest() {
        Menu menu = new Menu();
        menu.selectDown();
        menu.selectDown();
        menu.selectDown();
        assertEquals("Play", menu.getSelectedOption());
        assertEquals(0, menu.getSelectedIndex());
    }
}
