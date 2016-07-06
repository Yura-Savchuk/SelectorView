package com.coulddog.selectorview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by macbookpro on 04.07.16.
 */
public class ViewCompatUtil {

    @SuppressWarnings("deprecation")
    static void setTextAppearanceToTextView(@NonNull TextView textView, int textAppearanceStyle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(textAppearanceStyle);
        } else {
            textView.setTextAppearance(textView.getContext(), textAppearanceStyle);
        }
    }

    static int getColor(@NonNull Context context, int colorResId) {
        int color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getResources().getColor(colorResId, null);
        } else {
            color = context.getResources().getColor(colorResId);
        }
        return color;
    }

}
