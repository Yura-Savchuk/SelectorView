package com.coulcod.selectorview;

import android.support.annotation.NonNull;

import java.util.List;

public interface DialogDelegate {

    void showDialog(@NonNull DialogProperties properties, @NonNull SelectionDialogDelegate callback);

}
