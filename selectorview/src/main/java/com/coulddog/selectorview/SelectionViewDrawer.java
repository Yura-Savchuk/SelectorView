package com.coulddog.selectorview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * Created by macbookpro on 04.07.16.
 */
public class SelectionViewDrawer {

    private static final int WIDTH = 8;

    private final Paint paint = new Paint();
    private final Rect arrowContainer = new Rect(0, 0, 0, 0);
    private final Line line1 = new Line();
    private final Line line2 = new Line();

    public SelectionViewDrawer(int color) {
        paint.setColor(color);
        paint.setStrokeWidth(WIDTH);
    }

    public void setArrowContainer(int left, int top, int right, int bottom) {
        this.arrowContainer.left = left;
        this.arrowContainer.top = top;
        this.arrowContainer.right = right;
        this.arrowContainer.bottom = bottom;

        line1.startX = ((float) arrowContainer.width() * 0.3f) + (float) arrowContainer.left;
        line1.startY = (float) arrowContainer.height() * 0.2f;
        line1.endX = ((float) arrowContainer.width() * 0.6f) + (float) arrowContainer.left;
        line1.endY = (float) arrowContainer.height() * 0.5f;

        int displacement = getDisplacement();
        line2.startX = ((float) arrowContainer.width() * 0.3f) + (float) arrowContainer.left;
        line2.startY = (float) arrowContainer.height() * 0.8f;
        line2.endX = ((float) arrowContainer.width() * 0.6f) + (float) arrowContainer.left + displacement;
        line2.endY = (float) arrowContainer.height() * 0.5f - displacement;
    }

    public void onDraw(@NonNull Canvas canvas) {
        canvas.drawLine(line1.startX, line1.startY, line1.endX, line1.endY, paint);
        canvas.drawLine(line2.startX, line2.startY, line2.endX, line2.endY, paint);
    }

    /**
     * Calculate displacement for {@link SelectionViewDrawer#line2}.
     *
     * @return displacement dependent of line width.
     */
    private int getDisplacement() {
        double a = (double) WIDTH / 2;
        double b = (double) WIDTH / 2;
        double c = Math.sqrt(a*a+b*b); // Квадрат гіпотенузи дорівнює сумі квадратів катетів.
        double p = 0.5 * (a + b + c); //Число P
        double h = 2 * Math.sqrt(p * (p - a) * (p - b) * (p - c)) / c; //Висота трикутника
        return (int) h;
    }

    private static class Line {
        private float startX;
        private float startY;
        private float endX;
        private float endY;
        public Line() {
        }
    }

}
