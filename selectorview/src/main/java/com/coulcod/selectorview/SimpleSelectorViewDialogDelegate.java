package com.coulcod.selectorview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by seotm on 10.02.17.
 */

public class SimpleSelectorViewDialogDelegate implements SelectorViewDialogDelegate {

    private final Activity activity;

    public SimpleSelectorViewDialogDelegate(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showDialog(@NonNull List<? extends Checkable> values, @NonNull String title, @NonNull SelectionMode mode, @NonNull SelectionDialogDelegate callback) {
        if (values.size() != 0) {
            final AlertDialog dialog = new SelectionViewDialog.Builder(activity)
                    .setValues(values)
                    .setTitle(title)
                    .setSelectionMode(mode)
                    .setSelectionDialogDelegate(callback)
                    .create();
            dialog.show();
        }
    }
}
