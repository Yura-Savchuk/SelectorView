# SelectorView

An android custom view for single and multiple items selection. This is super easy to use and looks contains modern design. 

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
        SelectorViewAdapter adapter = new SelectorViewAdapter(this, testList);
        selectorView.setAdapter(adapter);
    }
 ```
# Add SelectorView to your project
Gradle:
``` java
compile 'com.coulcod.selectorview:selectorview:1.0.3'
 ```
