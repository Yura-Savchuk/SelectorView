package com.coulddog.selectorview;

import android.graphics.Color;
import android.support.annotation.NonNull;

/**
 * Created by macbookpro on 06.07.16.
 */
public class SelectorViewController {

    static final int EMPTY = -1;

    int titleTextAppearance;
    int valueTextAppearance;

    int titleTextSize;
    int valuesTextSize;

    int titleTextColor;
    int valuesTextColor;

    CharSequence titleText;

    boolean showDefaultArrow;

    SelectionMode selectionMode;
    SelectorViewMode selectorViewMode;
    SelectionViewDrawer selectionViewDrawer;

}
