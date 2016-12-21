# SelectorView

An android custom view for single and multiple items selection.

# Preview

![Preview](https://raw.githubusercontent.com/coulCod/SelectorView/master/app/1.png)
![Preview](https://github.com/coulCod/SelectorView/blob/master/app/2.png?raw=true)

# Usage
##In Layout
``` xml
<com.coulcod.selectorview.SelectorView
        android:id="@+id/selectorView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white"
        android:padding="5dp"
        app:svTitleTextSize="15sp"
        app:svValuesTextSize="15sp"
        app:svTitleTextColor="#727272"
        app:svValueTextColor="@android:color/black"
        app:svShowDefaultArrow="true"
        app:svSelectionMode="multiple"
        app:svSelectorViewMode="appearsTextField"
        />
```
##In Source
in **Activity of Fragment**
``` java

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
                        .setSelectionDialogDelegate(callback)
                        .setMultipleChoiceLayout(R.layout.select_multiple_choice)
                        .setListViewLayout(R.layout.select_list_view)
                        .create();
                dialog.show();
            }
        }
    };
 ```
# Add SelectorView to your project
Gradle:
``` java
compile 'com.coulcod.selectorview:selectorview:1.0.1'
 ```
