package com.coulddog.selectorview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by macbookpro on 04.07.16.
 */
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
