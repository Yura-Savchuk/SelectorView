package com.coulcod.valuepicker;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.coulcod.selectorview.CheckableString;
import com.coulcod.selectorview.SelectDialogDelegate;
import com.coulcod.selectorview.SelectionDialogDelegate;
import com.coulcod.selectorview.SelectorView;
import com.coulcod.selectorview.SelectorViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

    private static final List<CheckableString> cars = new ArrayList<CheckableString>() {{
        add(new CheckableString("Lexus"));
        add(new CheckableString("Mercedes"));
        add(new CheckableString("Ferrari"));
        add(new CheckableString("Dodge"));
        add(new CheckableString("Wolksvagen"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_range);
        selectValueWithSelectorView();
        selectValueWithTextView();
    }

    private void selectValueWithSelectorView() {
        SelectorView selectorView = (SelectorView) findViewById(R.id.selectorView);
        SelectorViewAdapter adapter = new SelectorViewAdapter(this, testList);
        selectorView.setAdapter(adapter);
    }

    private void selectValueWithTextView() {
        final TextView textView = (TextView) findViewById(R.id.textView);
        final SelectDialogDelegate dialogDelegate = new SelectDialogDelegate(this);
        dialogDelegate.setValues(cars);
        dialogDelegate.setTitle("Cars");
        dialogDelegate.setSelectionDialogDelegate(new SelectionDialogDelegate() {
            @Override
            public void onComplete() {
                textView.setText(getSelectedText());
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelegate.showDialog();
            }
        });
    }

    private String getSelectedText() {
        for (CheckableString car : cars) {
            if (car.isSelected()) {
                return car.getText();
            }
        }
        return getString(R.string.select_car);
    }

}
