package com.coulcod.selectorview;

import android.support.annotation.NonNull;

import java.util.List;

public interface SelectorViewDialogDelegate {

    void showDialog(@NonNull List<? extends Checkable> values, @NonNull String title, @NonNull SelectionMode mode, @NonNull SelectionDialogDelegate callback);

}
