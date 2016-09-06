package com.coulcod.selectorview;

public enum SelectionMode {
    Single,
    Multiple,
    SingleNoDeselect;

    public static SelectionMode fromInt(int value) {
        SelectionMode mode = Single;
        if (value == 2) {
            mode = Multiple;
        } else if (value == 3) {
            mode = SingleNoDeselect;
        }
        return mode;
    }

    public int toInt() {
        int value = 1;
        if (this == Multiple) {
            value = 2;
        } else if (this == SingleNoDeselect) {
            value = 3;
        }
        return value;
    }

}
