package com.coulddog.selectorview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by macbookpro on 04.07.16.
 */
public interface SelectorViewDialogDelegate {

    void showDialog(@NonNull List<? extends Checkable> values, @NonNull String title, @NonNull SelectionMode mode, @NonNull SelectionDialogCallback callback);

}
