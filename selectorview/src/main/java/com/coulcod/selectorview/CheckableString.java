package com.coulcod.selectorview;

import android.support.annotation.NonNull;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckableString that = (CheckableString) o;

        if (checked != that.checked) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }

}
