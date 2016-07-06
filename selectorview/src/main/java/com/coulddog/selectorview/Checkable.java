package com.coulddog.selectorview;

import android.support.annotation.NonNull;

/**
 * Created by macbookpro on 06.07.16.
 */
public interface Checkable {

    void setSelected(boolean selected);

    boolean isSelected();

    @NonNull String getText();

    Checkable copy();

}
