package br.com.zup.drawovercircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rafaelneiva on 13/11/16.
 */

public class DrawOverCircleView extends View {
    private TextPaint mTextPaint;
    private Paint mCirclePaint;
    private RectF mCircleRect;
    private float mHeight;
    private float mWidth;
    private float mCirclePadding = 160;

    public DrawOverCircleView(Context context) {
        super(context);
        init();
    }

    public DrawOverCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.DKGRAY);
        mTextPaint.setTextSize(24f);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.GRAY);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = (mCircleRect.width() / 2);
        float cx = mCircleRect.centerX();
        float cy = mCircleRect.centerY();
        float angle = (float) Math.toRadians(135); // TODO pass angle through param
        float scaleMarkSize = radius - getResources().getDisplayMetrics().density * 8;

        canvas.save();

        canvas.drawArc(mCircleRect, 0, 360, false, mCirclePaint);

        float xPos = (float) (cx + radius * Math.sin(angle));
        float yPos = (float) (cy - radius * Math.cos(angle));

        canvas.drawText(String.format("%.0f", Math.toDegrees(angle)) + " Jeferson Viado", xPos, yPos, mTextPaint);

        float stopX = (float) (cx + (radius - scaleMarkSize) * Math.sin(angle));
        float stopY = (float) (cy - (radius - scaleMarkSize) * Math.cos(angle));
        canvas.drawLine(xPos, yPos, stopX, stopY, mCirclePaint);

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = this.getHeight();
        mWidth = this.getWidth();

        mCircleRect = new RectF(0, 0, mWidth - mCirclePadding, mHeight - mCirclePadding);
    }
}
