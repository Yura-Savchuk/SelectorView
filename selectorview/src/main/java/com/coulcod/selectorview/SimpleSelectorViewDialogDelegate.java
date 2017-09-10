package com.coulcod.selectorview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by seotm on 10.02.17.
 */

public class SimpleSelectorViewDialogDelegate implements DialogDelegate {

    private final Activity activity;

    public SimpleSelectorViewDialogDelegate(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showDialog(@NonNull DialogProperties properties, @NonNull SelectionDialogDelegate callback) {
        if (!properties.isEmptyValues()) {
            final AlertDialog dialog = new SelectionViewDialogBuilder(activity)
                    .setProperties(properties)
                    .setSelectionDialogDelegate(callback)
                    .create();
            dialog.show();
        }
    }
}
