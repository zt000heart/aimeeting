package com.meeting.zhangtao21.meetingmanagement.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/2/13 0013.
 */
public class CircleImageView extends ImageView {
    int width;
    int heigh;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null == drawable) {
            return;
        }
        // ��drawableת����bitmap
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);

        Canvas srcCanvas = new Canvas(bitmap);
        drawable.draw(srcCanvas);

        float cx = getWidth() / 2;
        float cy = getHeight() / 2;

        float radius = Math.min(getWidth(), getHeight()) / 2;

        Paint borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(Color.GREEN);

        canvas.drawCircle(cx, cy, radius, borderPaint);
        // ��ͼ
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);
        canvas.drawCircle(cx, cy, radius - 5, paint);
    }
}
