package com.coulddog.selectorview;

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

import java.util.List;

public class SelectionViewDialog extends AlertDialog {

    private static final String DEF_APPLY = "Apply";
    private static final String DEF_CENCEL = "Cancel";

    private SelectionDialogCallback callback;
    private SelectionMode selectionMode;

    private SelectionViewDialog(Context context, boolean cancelable, OnCancelListener cancelListener, SelectionDialogCallback callback) {
        super(context, cancelable, cancelListener);
        this.callback = callback;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectionMode == SelectionMode.Single) {
                if (callback != null) callback.onComplete();
                dismiss();
            }
        }
    };

    private OnClickListener onApplyClickListener = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (callback != null) callback.onComplete();
            dismiss();
        }
    };

    public static class Builder {

        private static final int EMPTY = -1;

        private Context context;
        private String title;
        private SelectionMode selectionMode;
        private List<? extends com.coulddog.selectorview.Checkable> values;
        private String applyButtonText = DEF_APPLY;
        private String closeButtonText = DEF_CENCEL;
        private SelectionDialogCallback selectionDialogCallback;
        private int singleChoiceLayout = EMPTY;
        private int multipleChoiceLayout = EMPTY;
        private int listViewLayout = R.layout.default_list_view;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        @NonNull
        public Builder setSelectionMode(@NonNull SelectionMode selectionMode) {
            this.selectionMode = selectionMode;
            return this;
        }

        @NonNull
        public Builder setValues(@NonNull List<? extends com.coulddog.selectorview.Checkable> values) {
            this.values = values;
            return this;
        }

        @NonNull
        public Builder setApplyButtonText(@NonNull String text) {
            this.applyButtonText = text;
            return this;
        }

        @NonNull
        public Builder setCloseButtonText(@NonNull String text) {
            this.closeButtonText = text;
            return this;
        }

        @NonNull
        public Builder setSelectionDialogCallback(@NonNull SelectionDialogCallback selectionDialogCallback) {
            this.selectionDialogCallback = selectionDialogCallback;
            return this;
        }

        @NonNull
        public Builder setSingleChoiceLayout(int singleChoiceLayout) {
            this.singleChoiceLayout = singleChoiceLayout;
            return this;
        }

        @NonNull
        public Builder setListViewLayout(int listViewLayout) {
            this.listViewLayout = listViewLayout;
            return this;
        }

        /**
         * Set layout for multiple item in dialog {@link ListView}.
         * @param multipleChoiceLayout Layout file must contain
         * view with id @android.R.id.button2, that view must extended from {@link TextView} and implemented
         * interface {@link Checkable}.
         * @return {@link Builder}
         */
        @NonNull
        public Builder setMultipleChoiceLayout(int multipleChoiceLayout) {
            this.multipleChoiceLayout = multipleChoiceLayout;
            return this;
        }

        @NonNull public SelectionViewDialog create() {
            ListView listView = createListView(context, selectionMode, values, singleChoiceLayout, multipleChoiceLayout, listViewLayout);
            final SelectionViewDialog dialog = new SelectionViewDialog(context, true, null, selectionDialogCallback);
            dialog.setTitle(title);
            float dpi = context.getResources().getDisplayMetrics().density;
            dialog.setView(listView, (int)(24*dpi), (int)(5*dpi), (int)(24*dpi), (int)(5*dpi) );
            if (selectionMode == SelectionMode.Multiple) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, applyButtonText, dialog.onApplyClickListener);
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, closeButtonText, (OnClickListener)null);
            }
            listView.setOnItemClickListener(dialog.onItemClickListener);
            dialog.selectionMode = selectionMode;
            return dialog;
        }

    }

    @NonNull private static ListView createListView(@NonNull Context context, @NonNull SelectionMode selectionMode, @NonNull List<? extends com.coulddog.selectorview.Checkable> values, int singleCoiceLayout, int multipleChoiceAdapter, int listViewLayout) {
        ListView listView = (ListView) LayoutInflater.from(context).inflate(listViewLayout, null);
        DialogListViewAdapter adapter = new DialogListViewAdapter(values, selectionMode);
        if (singleCoiceLayout != Builder.EMPTY) adapter.setSingleChoiceLayout(singleCoiceLayout);
        if (multipleChoiceAdapter != Builder.EMPTY) adapter.setMultipleChoiceLayout(multipleChoiceAdapter);
        listView.setAdapter(adapter);
        adapter.setListView(listView);
        return listView;
    }

}
