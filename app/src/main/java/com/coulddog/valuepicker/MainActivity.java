package com.coulddog.valuepicker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coulddog.selectorview.Checkable;
import com.coulddog.selectorview.CheckableString;
import com.coulddog.selectorview.SelectionDialogCallback;
import com.coulddog.selectorview.SelectionMode;
import com.coulddog.selectorview.SelectionViewDialog;
import com.coulddog.selectorview.SelectorView;
import com.coulddog.selectorview.SelectorViewAdapter;
import com.coulddog.selectorview.SelectorViewDialogDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String title = "Animals";
    private static final List<CheckableString> testList = new ArrayList<CheckableString>() {{
        add(new CheckableString("Cow"));
        add(new CheckableString("Rabbit"));
        add(new CheckableString("Fox"));
        add(new CheckableString("Dog"));
        add(new CheckableString("Lion"));
        add(new CheckableString("Puma"));
        add(new CheckableString("Bear"));
        add(new CheckableString("Cat"));
        add(new CheckableString("Mouse"));
        add(new CheckableString("Chicken"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_range);

        SelectorView selectorView = (SelectorView) findViewById(R.id.selectorView);
        SelectorViewAdapter adapter = new SelectorViewAdapter(selectorViewDialogDelegate, testList);
        adapter.setTitle(title);
        assert selectorView != null;
        selectorView.setAdapter(adapter);

    }

    private SelectorViewDialogDelegate selectorViewDialogDelegate = new SelectorViewDialogDelegate() {
        @Override
        public void showDialog(@Nullable List<? extends Checkable> values, @NonNull String title, @NonNull SelectionMode mode, @NonNull SelectionDialogCallback callback) {
            if (values != null && values.size() != 0) {
                final AlertDialog dialog = new SelectionViewDialog.Builder(MainActivity.this)
                        .setValues(values)
                        .setTitle(title)
                        .setSelectionMode(mode)
                        .setSelectionDialogCallback(callback)
                        .setMultipleChoiceLayout(R.layout.select_multiple_choice)
                        .setListViewLayout(R.layout.select_list_view)
                        .create();
                dialog.show();
            }
        }
    };

}
