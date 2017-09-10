package com.coulcod.selectorview;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by seotm on 10.09.17.
 */

public class InputTextWatcher implements TextWatcher {

    private final static int EMPTY_POSITION = -1;

    private final ListView listView;
    private final List<? extends Checkable> values;

    public InputTextWatcher(ListView listView, List<? extends Checkable> values) {
        this.listView = listView;
        this.values = values;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String query = s.toString().toLowerCase();
        int valueItemPosition = getValuePositon(query);
        if (valueItemPosition == EMPTY_POSITION) {
            return;
        }
        setValueCheckedAtPosition(valueItemPosition);
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        listView.setSelection(valueItemPosition);
    }

    private int getValuePositon(String query) {
        for (int i=0; i<values.size(); i++) {
            Checkable item = values.get(i);
            if (item.getText().toLowerCase().equals(query)) {
                return i;
            }
        }
        return EMPTY_POSITION;
    }

    private void setValueCheckedAtPosition(int position) {
        for (int i=0; i<values.size(); i++) {
            Checkable item = values.get(i);
            if (i == position) {
                item.setSelected(true);
            } else if (item.isSelected()) {
                item.setSelected(false);
            }
        }
    }

}
