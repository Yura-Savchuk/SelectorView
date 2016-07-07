package com.coulddog.valuepicker;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.coulddog.selectorview.Checkable;
import com.coulddog.selectorview.CheckableString;
import com.coulddog.selectorview.SelectionDialogCallback;
import com.coulddog.selectorview.SelectionMode;
import com.coulddog.selectorview.SelectionViewDialog;
import com.coulddog.selectorview.SelectorView;
import com.coulddog.selectorview.SelectorViewAdapter;
import com.coulddog.selectorview.SelectorViewDialogDelegate;
import com.coulddog.selectorview.SelectorViewMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String title = "Номер";
    private static final List<CheckableString> testList = new ArrayList<CheckableString>() {{
        add(new CheckableString("Первый"));
        add(new CheckableString("Второй"));
        add(new CheckableString("Третий"));
        add(new CheckableString("Четвертый"));
        add(new CheckableString("Пятый"));
        add(new CheckableString("Шестой"));
        add(new CheckableString("Седьмой"));
        add(new CheckableString("Восьмой"));
        add(new CheckableString("Девятый"));
        add(new CheckableString("Десятый"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_range);

        SelectorView selectorView = (SelectorView) findViewById(R.id.selectorView);
        SelectorViewAdapter adapter = new SelectorViewAdapter(selectorViewDialogDelegate, testList);
//        adapter.setTitle(title);
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
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface d) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#546efe"));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#546efe"));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                    }
                });
                dialog.show();
            }
        }
    };

}
