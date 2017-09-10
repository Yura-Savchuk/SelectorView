package com.coulcod.selectorview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by seotm on 10.09.17.
 */

public class SelectionViewDialogBuilder {

    private Context context;
    private DialogContentViewBuilder dialogContentViewBuilder;
    private String title;
    private SelectionMode selectionMode;
    private String applyButtonText = "Принять";
    private String closeButtonText = "Отмена";
    private SelectionDialogDelegate selectionDialogDelegate;

    public SelectionViewDialogBuilder(@NonNull Context context) {
        this.context = context;
        this.dialogContentViewBuilder = new DialogContentViewBuilder(context);
    }

    public SelectionViewDialogBuilder setProperties(DialogProperties properties) {
        this.title = properties.title;
        this.selectionMode = properties.mode;
        this.dialogContentViewBuilder.values = properties.getValues();
        this.dialogContentViewBuilder.inputValue = properties.inputValue;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setSelectionMode(@NonNull SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setValues(@NonNull List<? extends com.coulcod.selectorview.Checkable> values) {
        this.dialogContentViewBuilder.values = values;
        return this;
    }

    public SelectionViewDialogBuilder setInputValue(boolean inputValue) {
        this.dialogContentViewBuilder.inputValue = inputValue;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setApplyButtonText(@NonNull String text) {
        this.applyButtonText = text;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setCloseButtonText(@NonNull String text) {
        this.closeButtonText = text;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setSelectionDialogDelegate(@NonNull SelectionDialogDelegate selectionDialogDelegate) {
        this.selectionDialogDelegate = selectionDialogDelegate;
        return this;
    }

    @NonNull
    public SelectionViewDialogBuilder setSingleChoiceLayout(int singleChoiceLayout) {
        this.dialogContentViewBuilder.singleChoiceLayout = singleChoiceLayout;
        return this;
    }

    /**
     * Set layout for multiple item in dialog {@link ListView}.
     * @param multipleChoiceLayout Layout file must contain
     * view with id @android.R.id.button2, that view must extended from {@link TextView} and implemented
     * interface {@link android.widget.Checkable}.
     * @return {@link SelectionViewDialog.Builder}
     */
    @NonNull
    public SelectionViewDialogBuilder setMultipleChoiceLayout(int multipleChoiceLayout) {
        this.dialogContentViewBuilder.multipleChoiceLayout = multipleChoiceLayout;
        return this;
    }

    public SelectionViewDialog create() {
        final SelectionViewDialog dialog = new SelectionViewDialog(context, true, null, selectionDialogDelegate);
        dialogContentViewBuilder.selectionMode = selectionMode;
        dialogContentViewBuilder.onItemClickListener = dialog.onItemClickListener;
        dialog.setTitle(title);
        float dpi = context.getResources().getDisplayMetrics().density;
        dialog.setView(dialogContentViewBuilder.createContentView(), (int)(24*dpi), (int)(5*dpi), (int)(24*dpi), (int)(5*dpi) );
        if (selectionMode == SelectionMode.Multiple) {
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, applyButtonText, dialog.onApplyClickListener);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, closeButtonText, (DialogInterface.OnClickListener)null);
        }
        dialog.selectionMode = selectionMode;
        return dialog;
    }

}
