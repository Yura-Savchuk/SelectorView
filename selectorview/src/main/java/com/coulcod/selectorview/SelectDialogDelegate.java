package com.coulcod.selectorview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by {@author coulcod} on 10.09.17.
 */

public class SelectDialogDelegate implements SelectionDialogDelegate {

    private final DialogDelegate delegate;
    SelectionDialogDelegate selectionDialogDelegate;
    final DialogProperties props = new DialogProperties();

    public SelectDialogDelegate(DialogDelegate delegate) {
        this.delegate = delegate;
    }

    public SelectDialogDelegate(Activity activity) {
        this(new SimpleSelectorViewDialogDelegate(activity));
    }

    public void setValues(@Nullable List<? extends Checkable> values) {
        if (values == null) {
            props.values = null;
        }
        props.values = new CheckableValues(values);
    }

    public List<? extends Checkable> getValues() {
        if (props.values == null) {
            return null;
        }
        return props.values.values;
    }

    public void setSelectionDialogDelegate(SelectionDialogDelegate selectionDialogDelegate) {
        this.selectionDialogDelegate = selectionDialogDelegate;
    }

    public void setTitle(String title) {
        props.title = title;
    }

    public void setSelectionMode(@NonNull SelectionMode selectionMode) {
        props.mode = selectionMode;
    }

    public void setInputValue(boolean inputValue) {
        props.inputValue = inputValue;
    }

    public void showDialog() {
        if (props.values == null) {
            throw new IllegalArgumentException("Missing values. Set values with a method setValues(List) before showing dialog.");
        }
        delegate.showDialog(props, this);
    }

    @Override
    public void onComplete() {
        if (selectionDialogDelegate == null) {
            throw new IllegalArgumentException("Missing selection dialog delegate. Set selectionDialogDelegate with a method setSelectionDialogDelegate(SelectionDialogDelegate) before showing dialog.");
        }
        props.values.updateSelectedValues();
        selectionDialogDelegate.onComplete();
    }
}
