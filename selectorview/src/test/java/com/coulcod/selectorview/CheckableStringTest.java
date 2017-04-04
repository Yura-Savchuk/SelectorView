package com.coulcod.selectorview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by seotm on 04.04.17.
 */

public class CheckableStringTest {

    @Test
    public void checkable_methods_isCorrect() {
        String text = "Testable text";
        CheckableString checkableStr = new CheckableString(text);
        //test initialization state
        assertEquals(checkableStr.getText(), text);
        assertFalse(checkableStr.isSelected());
        //test change selection state
        checkableStr.setSelected(true);
        assertTrue(checkableStr.isSelected());
        //test copy behaviour
        CheckableString copy = (CheckableString) checkableStr.copy();
        assertTrue(copy != checkableStr);
        assertEquals(copy, checkableStr);
    }

}
