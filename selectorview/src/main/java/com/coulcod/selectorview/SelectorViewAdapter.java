package com.coulcod.selectorview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SelectorViewAdapter extends SelectDialogDelegate {

    private SelectorView selectorView;

    /**
     * Use {@link #SelectorViewAdapter(DialogDelegate)} and {@link SelectorViewAdapter#setValues(List)} instead.
     */
    @Deprecated
    public SelectorViewAdapter(@Nullable List<String> values, @NonNull DialogDelegate delegate) {
        super(delegate);
        if (values != null) {
            List<CheckableString> checkableStringValues = new ArrayList<>();
            for (String value : values) {
                checkableStringValues.add(new CheckableString(value));
            }
            setValues(checkableStringValues);
        }
    }

    public SelectorViewAdapter(@NonNull DialogDelegate delegate) {
        super(delegate);
    }

    /**
     * Use {@link #SelectorViewAdapter(DialogDelegate)} and {@link SelectorViewAdapter#setValues(List)} instead.
     */
    @Deprecated
    public SelectorViewAdapter(@NonNull DialogDelegate delegate, @Nullable List<? extends Checkable> values) {
        super(delegate);
        setValues(values);
    }

    /**
     * Use {@link #SelectorViewAdapter(Activity)} and {@link SelectorViewAdapter#setValues(List)} instead.
     */
    @Deprecated
    public SelectorViewAdapter(@NonNull Activity activity, @Nullable List<? extends Checkable> values) {
        super(activity);
        setValues(values);
    }

    public SelectorViewAdapter(@NonNull Activity activity) {
        super(activity);
    }

    public void notifyDataSetChanged() {
        if (selectorView != null) {
            selectorView.updateViewState();
        }
    }

    {
        selectionDialogDelegate = new SelectionDialogDelegate() {

            @Override
            public void onComplete() {
                notifyDataSetChanged();
                if (selectorView != null && selectorView.onValuesChangeListener != null) {
                    selectorView.onValuesChangeListener.onValueChanges(selectorView, getValues());
                }
            }
        };
    }

    View.OnClickListener onSelectorViewClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };

    void setSelectorView(@Nullable SelectorView selectorView) {
        this.selectorView = selectorView;
    }

}
