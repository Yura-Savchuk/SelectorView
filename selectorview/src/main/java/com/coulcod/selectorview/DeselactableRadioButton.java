package com.coulcod.selectorview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * Created by {@author coulcod} on 10.09.17.
 */

public class DeselactableRadioButton extends CompoundButton {

    public DeselactableRadioButton(Context context) {
        this(context, null);
    }

    public DeselactableRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.radioButtonStyle);
    }

    public DeselactableRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return DeselactableRadioButton.class.getName();
    }

}
