package com.coulddog.selectorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.coulddog.selectorview.SelectorViewController.EMPTY;

public class SelectorView extends RelativeLayout {

    public SelectorView(Context context) {
        this(context, null);
    }

    public SelectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        titleTextView = new TextView(context);
        valuesTextView = new TextView(context);

        P = new SelectorViewController();

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SelectorView,
                0, 0);

        P.titleTextAppearance = a.getResourceId(R.styleable.SelectorView_svTitleTextAppearance, EMPTY);
        P.valueTextAppearance = a.getResourceId(R.styleable.SelectorView_svValuesTextAppearance, EMPTY);

        P.titleTextSize = a.getDimensionPixelSize(R.styleable.SelectorView_svTitleTextSize, EMPTY);
        P.valuesTextSize = a.getDimensionPixelSize(R.styleable.SelectorView_svValuesTextSize, EMPTY);

        P.titleTextColor = a.getColor(R.styleable.SelectorView_svTitleTextColor, EMPTY);
        P.valuesTextColor = a.getColor(R.styleable.SelectorView_svValueTextColor, EMPTY);

        P.titleText = a.getText(R.styleable.SelectorView_svTitleText);

        P.showDefaultArrow = a.getBoolean(R.styleable.SelectorView_svShowDefaultArrow, true);

        P.selectorViewMode = SelectorViewMode.fromInt(a.getInt(R.styleable.SelectorView_svSelectorViewMode, 1));
        P.selectionMode = SelectionMode.fromInt(a.getInt(R.styleable.SelectorView_svSelectionMode, 1));

        P.selectionViewDrawer = new SelectionViewDrawer(a.getColor(R.styleable.SelectorView_svArrowColor,
                ViewCompatUtil.getColor(context,android.R.color.holo_blue_light)));

        a.recycle();

        initViews();
    }

    @SuppressWarnings("deprecation")
    private void initViews() {
        titleTextView.setId(android.R.id.text1);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(titleTextView, params);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(BELOW, android.R.id.text1);
        addView(valuesTextView,params);


        if (P.titleTextAppearance != EMPTY) ViewCompatUtil.setTextAppearanceToTextView(titleTextView, P.titleTextAppearance);
        if (P.valueTextAppearance != EMPTY) ViewCompatUtil.setTextAppearanceToTextView(valuesTextView, P.valueTextAppearance);

        if (P.titleTextSize != EMPTY) titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, P.titleTextSize);
        if (P.valuesTextSize != EMPTY) valuesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, P.valuesTextSize);

        if (P.titleTextColor != EMPTY) titleTextView.setTextColor(P.titleTextColor);
        if (P.valuesTextColor != EMPTY) valuesTextView.setTextColor(P.valuesTextColor);

    }

    private final TextView titleTextView;
    private final TextView valuesTextView;
    private final SelectorViewController P;
    private SelectorViewAdapter adapter;
    private boolean drawArrow = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawArrow && P.showDefaultArrow) {
            P.selectionViewDrawer.onDraw(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            int textSpacing = Math.abs(t - b);
            RelativeLayout.LayoutParams params = (LayoutParams) titleTextView.getLayoutParams();
            params.rightMargin = textSpacing;
            titleTextView.setLayoutParams(params);
            P.selectionViewDrawer.setArrowContainer(getWidth() - getHeight(), 0, getWidth(), getHeight());
        }
        boolean drawArrow = valuesTextView.getMeasuredWidth() <= titleTextView.getMeasuredWidth();
        if (drawArrow && !this.drawArrow || !drawArrow && this.drawArrow) {
            this.drawArrow = drawArrow;
            invalidate();
        }
    }

    public void setAdapter(@Nullable SelectorViewAdapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            if (P.titleText != null) adapter.title = (String) P.titleText;
            if (adapter.selectionMode == null) adapter.selectionMode = P.selectionMode;
            adapter.setSelectorView(this);
            setOnClickListener(adapter.onSelectorViewClick);
            adapter.notifyDataSetChanged();
        }
    }

    void updateViewState() {
        if (adapter != null) {
            String titleText = adapter.title;
            if (titleText == null) {
                throw new IllegalArgumentException("Please, set title through SelectorViewAdapter " +
                        "or SelectorView.setTitle(), or in view layout.");
            }
            String selectedText = "";
            for (Checkable item : adapter.getValues()) {
                if (item.isSelected()) {
                    if (selectedText.isEmpty()) {
                        selectedText = item.getText();
                        if (adapter.selectionMode == SelectionMode.Single) {
                            break;
                        }
                    } else {
                        selectedText += ", " + item.getText();
                    }
                }
            }


            if (P.selectorViewMode == SelectorViewMode.TwoTextField) {

            } else if (!selectedText.isEmpty()) {
                titleTextView.setText(selectedText);
            }


            if (P.selectorViewMode == SelectorViewMode.TwoTextField) {
                titleTextView.setText(titleText);
                valuesTextView.setText(selectedText);
            } else if (P.selectorViewMode == SelectorViewMode.OneTextField) {
                if (selectedText.isEmpty()) {
                    titleTextView.setText(titleText);
                }
            } else if (P.selectorViewMode == SelectorViewMode.AppearsTextField) {
                if (selectedText.isEmpty()) {
                    titleTextView.setText("");
                    valuesTextView.setText(titleText);
                    valuesTextView.setTextColor(P.titleTextColor);
                } else {
                    titleTextView.setText(titleText);
                    titleTextView.setTextColor(P.titleTextColor);
                    valuesTextView.setText(selectedText);
                    valuesTextView.setTextColor(P.valuesTextColor);
                }
            }
            invalidate();
        }
    }

    public void setShowDefaultArrow(boolean showDefaultArrow) {
        P.showDefaultArrow = showDefaultArrow;
        invalidate();
    }

    public void setSelectorViewMode(@NonNull SelectorViewMode selectorViewMode) {
        P.selectorViewMode = selectorViewMode;
    }

}
