package com.coulcod.selectorview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import static com.coulcod.selectorview.SelectorViewController.EMPTY;

/**
 * Created by seotm on 10.09.17.
 */

class DialogContentViewBuilder {

    private static final int MIN_COUNT_FOR_INPUT_TYPE = 10;

    private final Context context;
    SelectionMode selectionMode;
    List<? extends Checkable> values;
    boolean inputValue;
    int singleChoiceLayout = EMPTY;
    int multipleChoiceLayout = EMPTY;
    AdapterView.OnItemClickListener onItemClickListener;

    DialogContentViewBuilder(Context context) {
        this.context = context;
    }

    View createContentView() {
        View view;
        if (inputValue && values.size() >= MIN_COUNT_FOR_INPUT_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_list_with_input_value, null);
            ListView listView = (ListView) view.findViewById(android.R.id.list);
            prepareEditText((EditText) view.findViewById(android.R.id.edit), listView);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        }
        prepareListView((ListView) view.findViewById(android.R.id.list));
        return view;
    }

    private void prepareEditText(EditText editText, ListView listView) {
        InputTextWatcher watcher = new InputTextWatcher(listView, values);
        editText.addTextChangedListener(watcher);
    }

    private void prepareListView(ListView listView) {
        DialogListViewAdapter adapter = new DialogListViewAdapter(values, selectionMode);
        if (singleChoiceLayout != EMPTY) adapter.setSingleChoiceLayout(singleChoiceLayout);
        if (multipleChoiceLayout != EMPTY) adapter.setMultipleChoiceLayout(multipleChoiceLayout);
        listView.setAdapter(adapter);
        adapter.setListView(listView);
        listView.setOnItemClickListener(onItemClickListener);
    }

}
