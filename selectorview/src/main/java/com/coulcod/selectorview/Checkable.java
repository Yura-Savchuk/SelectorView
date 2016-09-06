package com.coulcod.selectorview;

import android.support.annotation.NonNull;

public interface Checkable {

    void setSelected(boolean selected);

    boolean isSelected();

    @NonNull String getText();

    Checkable copy();

}
