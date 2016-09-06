package com.coulcod.selectorview;

import android.support.annotation.NonNull;

import java.util.List;

public interface OnValuesChangeListener {

    void onValueChanges(@NonNull SelectorView selectorView, @NonNull List<? extends Checkable> values);

}
