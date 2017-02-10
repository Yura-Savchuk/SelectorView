package com.coulcod.valuepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.coulcod.selectorview.Checkable;
import com.coulcod.selectorview.CheckableString;
import com.coulcod.selectorview.SelectionDialogDelegate;
import com.coulcod.selectorview.SelectionMode;
import com.coulcod.selectorview.SelectionViewDialog;
import com.coulcod.selectorview.SelectorView;
import com.coulcod.selectorview.SelectorViewAdapter;
import com.coulcod.selectorview.SelectorViewDialogDelegate;
import com.coulcod.selectorview.SimpleSelectorViewDialogDelegate;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_range);
        SelectorView selectorView = (SelectorView) findViewById(R.id.selectorView);
        SelectorViewAdapter adapter = new SelectorViewAdapter(this, testList);
        selectorView.setAdapter(adapter);
    }

}
