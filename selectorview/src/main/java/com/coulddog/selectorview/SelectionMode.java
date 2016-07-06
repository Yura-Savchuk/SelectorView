package com.coulddog.selectorview;

/**
 * Created by macbookpro on 04.07.16.
 */
public enum SelectionMode {
    Single,
    Multiple;

    public static SelectionMode fromInt(int value) {
        SelectionMode mode = Single;
        if (value == 2) {
            mode = Multiple;
        }
        return mode;
    }

    public int toInt() {
        int value = 1;
        if (this == Multiple) {
            value = 2;
        }
        return value;
    }

}
