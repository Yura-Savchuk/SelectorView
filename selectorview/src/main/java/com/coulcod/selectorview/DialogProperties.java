package com.coulcod.selectorview;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by seotm on 10.09.17.
 */

public class DialogProperties {

    CheckableValues values;
    String title;
    SelectionMode mode = SelectionMode.Single;
    boolean inputValue;

    public List<? extends Checkable> getValues() {
        return values.temporaryValues;
    }

    public String getTitle() {
        return title;
    }

    public SelectionMode getMode() {
        return mode;
    }

    public boolean isInputValue() {
        return inputValue;
    }

    public boolean isEmptyValues() {
        return values.temporaryValues.isEmpty();
    }

}
