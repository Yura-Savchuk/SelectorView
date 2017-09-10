package com.coulcod.selectorview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;
import static com.coulcod.selectorview.SelectorViewController.EMPTY;

import java.util.List;

public class SelectionViewDialog extends AlertDialog {

    private SelectionDialogDelegate callback;
    SelectionMode selectionMode;

    SelectionViewDialog(Context context, boolean cancelable, OnCancelListener cancelListener, SelectionDialogDelegate callback) {
        super(context, cancelable, cancelListener);
        this.callback = callback;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectionMode == SelectionMode.Single || selectionMode == SelectionMode.SingleNoDeselect) {
                if (callback != null) callback.onComplete();
                dismiss();
            }
        }
    };

    OnClickListener onApplyClickListener = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (callback != null) callback.onComplete();
            dismiss();
        }
    };

}
