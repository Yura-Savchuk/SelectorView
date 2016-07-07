package com.coulddog.selectorview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 04.07.16.
 */
public class SelectorViewAdapter {

    @Nullable
    private List<? extends Checkable> values;
    @NonNull
    private final List<? extends Checkable> temporaryValues;
    String title;
    @Nullable
    private SelectorView selectorView;
    @NonNull
    private final SelectorViewDialogDelegate delegate;
    SelectionMode selectionMode;

    public SelectorViewAdapter(@Nullable List<String> values, @NonNull SelectorViewDialogDelegate delegate) {
        this(delegate, values == null ? null : new ArrayList<Checkable>());
        if (values != null) {
            for (String value : values) {
                assert this.values != null;
                ((List<CheckableString>) this.values).add(new CheckableString(value));
            }
        }
    }

    public SelectorViewAdapter(@NonNull SelectorViewDialogDelegate delegate, @Nullable List<? extends Checkable> values) {
        this.values = values;
        this.temporaryValues = new ArrayList<>();
        this.delegate = delegate;
    }

    @Nullable
    List<? extends Checkable> getValues() {
        return values;
    }

    public void setValues(@Nullable List<? extends Checkable> values) {
        this.values = values;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setSelectionMode(@NonNull SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }

    public void notifyDataSetChanged() {
        if (selectorView != null) {
            selectorView.updateViewState();
        }
    }

    SelectionDialogCallback selectionDialogCallback = new SelectionDialogCallback() {
        @Override
        public void onComplete() {
            copyValues(temporaryValues, values);
            notifyDataSetChanged();
        }
    };

    View.OnClickListener onSelectorViewClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            copyValues(values, temporaryValues);
            delegate.showDialog(temporaryValues, title, selectionMode, selectionDialogCallback);
        }
    };

    void setSelectorView(@Nullable SelectorView selectorView) {
        this.selectorView = selectorView;
    }

    private static void copyValues(@NonNull List from, @NonNull List to) {
        to.clear();
        for (Object item : from) {
            to.add(((Checkable)item).copy());
        }
    }
}
