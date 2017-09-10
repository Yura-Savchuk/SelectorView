package com.coulcod.selectorview;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by {@author coulcod} on 10.09.17.
 */

class CheckableValues {

    final List<? extends Checkable> values;
    final List<? extends Checkable> temporaryValues;

    CheckableValues(List<? extends Checkable> values) {
        this.values = values;
        this.temporaryValues = new ArrayList<>();
        copyValues(values, this.temporaryValues);
    }

    @SuppressWarnings("unchecked")
    private static void copyValues(@NonNull List from, @NonNull List to) {
        to.clear();
        for (Object item : from) {
            to.add(((Checkable)item).copy());
        }
    }

    void updateSelectedValues() {
        copyValues(temporaryValues, values);
    }
}
