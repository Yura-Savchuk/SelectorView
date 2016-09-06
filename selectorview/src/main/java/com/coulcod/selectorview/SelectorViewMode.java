package com.coulcod.selectorview;

import android.support.annotation.NonNull;

public enum SelectorViewMode {
    TwoTextField,
    OneTextField,
    AppearsTextField;

    @NonNull public static SelectorViewMode fromInt(int value) {
        SelectorViewMode viewMode = TwoTextField;
        if (value == 2) {
            viewMode = OneTextField;
        } else if (value == 3) {
            viewMode = AppearsTextField;
        }
        return viewMode;
    }

    public int toInt() {
        int value = 1;
        if (this == OneTextField) {
            value = 2;
        } else if (this == AppearsTextField) {
            value = 3;
        }
        return value;
    }

}
