package com.coulddog.selectorview;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by macbookpro on 07.07.16.
 */
public interface OnValuesChangeListener {

    void onValueChanges(@NonNull SelectorView selectorView, @NonNull List<? extends Checkable> values);

}
