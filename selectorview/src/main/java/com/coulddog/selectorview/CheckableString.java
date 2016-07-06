package com.coulddog.selectorview;

import android.support.annotation.NonNull;

/**
 * Created by macbookpro on 04.07.16.
 */
public class CheckableString implements Checkable {

    private final String value;
    private boolean checked = false;

    public CheckableString(String value) {
        this.value = value;
    }

    @Override
    public void setSelected(boolean selected) {
        this.checked = selected;
    }

    @Override
    public boolean isSelected() {
        return checked;
    }

    @NonNull
    @Override
    public String getText() {
        return value;
    }

    @Override
    public Checkable copy() {
        CheckableString copy = new CheckableString(value);
        copy.checked = checked;
        return copy;
    }
}
