package com.coulcod.selectorview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by {@author coulcod} on 10.09.17.
 */

public class SelectDialogDelegate implements SelectionDialogDelegate {

    private CheckableValues values;
    private final SelectorViewDialogDelegate delegate;
    SelectionDialogDelegate selectionDialogDelegate;
    String title;
    SelectionMode selectionMode = SelectionMode.Single;

    public SelectDialogDelegate(SelectorViewDialogDelegate delegate) {
        this.delegate = delegate;
    }

    public SelectDialogDelegate(Activity activity) {
        this(new SimpleSelectorViewDialogDelegate(activity));
    }

    public void setValues(@Nullable List<? extends Checkable> values) {
        if (values == null) {
            this.values = null;
        }
        this.values = new CheckableValues(values);
    }

    public List<? extends Checkable> getValues() {
        if (values == null) {
            return null;
        }
        return values.values;
    }

    public void setSelectionDialogDelegate(SelectionDialogDelegate selectionDialogDelegate) {
        this.selectionDialogDelegate = selectionDialogDelegate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSelectionMode(@NonNull SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }

    public void showDialog() {
        if (values == null) {
            throw new IllegalArgumentException("Missing values. Set values with a method setValues(List) before showing dialog.");
        }
        delegate.showDialog(values.temporaryValues, title, selectionMode, this);
    }

    @Override
    public void onComplete() {
        if (selectionDialogDelegate == null) {
            throw new IllegalArgumentException("Missing selection dialog delegate. Set selectionDialogDelegate with a method setSelectionDialogDelegate(SelectionDialogDelegate) before showing dialog.");
        }
        values.updateSelectedValues();
        selectionDialogDelegate.onComplete();
    }
}
