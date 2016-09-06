package com.coulcod.selectorview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.TextView;

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
