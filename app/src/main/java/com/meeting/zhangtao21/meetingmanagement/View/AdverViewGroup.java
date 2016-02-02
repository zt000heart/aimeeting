package com.meeting.zhangtao21.meetingmanagement.View;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhangtao21 on 15/11/30.
 */
public class AdverViewGroup extends ViewGroup{

    private int childrenCount;
    private Scroller scroller;
    private int currentItem = 0;
    private int width;
    private boolean autoScroll=false;
    private OnClickListener onClickListener;


    float startX = 0;
    float x;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(autoScroll){
                next();
                sendEmptyMessageDelayed(0, 3000);
            }
        }
    };

    private OnPageChangListener onPageChangListener;

    public AdverViewGroup(Context context) {
        super(context);
    }

    public AdverViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdverViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(currentItem);
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int width = 0;
        int height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode){
            case MeasureSpec.AT_MOST:
                if(getChildCount()<=0){
                    width=0;
                }else{
                    width=getChildAt(0).getMeasuredWidth();
                }
                break;
            case MeasureSpec.EXACTLY:
                width = measureWidth;
                break;
        }
        switch (heightMode){
            case MeasureSpec.AT_MOST:
                if(getChildCount()<=0){
                    height=0;
                }else{
                    height=getChildAt(0).getMeasuredHeight();
                }
                break;
            case MeasureSpec.EXACTLY:
                height = measureHeight;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        width = getMeasuredWidth();
        childrenCount = getChildCount();
        for(int i=0;i<childrenCount;i++){
            getChildAt(i).layout(l+i*width,t,r+i*width,b);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                x=event.getRawX();
//                smoothScrollTo((int) (scroller.getFinalX()+startX-x),0);
                scroller.startScroll(scroller.getFinalX(),0, (int) (-x+startX),0,300);
                invalidate();
                startX=x;
                break;
            case MotionEvent.ACTION_UP:
                int location=scroller.getFinalX();
                if(location<0){
                    smoothScrollTo(0, 0);
                    currentItem=0;
                }
                else if(location>(childrenCount-1)*width){
                    smoothScrollTo((childrenCount-1)*width,0);
                }
                else if(location%width<=width/2){
                    smoothScrollTo(location-location%width,0);
                    if(location < currentItem*width){
                        currentItem--;
                        if(onPageChangListener!=null)
                        onPageChangListener.onPageChange(currentItem);
                    }
                }
                else{
                    smoothScrollTo(location-location%width+width,0);
                    if(location > currentItem*width){
                        currentItem++;
                        if(onPageChangListener!=null)
                        onPageChangListener.onPageChange(currentItem);
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    public void back(){
        currentItem--;
        if(currentItem<=0){
            currentItem=childrenCount-1;
        }
        smoothScrollTo(currentItem * width, 0);
        if(onPageChangListener!=null)
        onPageChangListener.onPageChange(currentItem);
    }
    public void next(){
        currentItem++;
        if(currentItem>=childrenCount){
            currentItem=0;
        }
        smoothScrollTo(currentItem * width, 0);
        if(onPageChangListener!=null)
        onPageChangListener.onPageChange(currentItem);

    }

    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - scroller.getFinalX();
        int dy = fy - scroller.getFinalY();
        scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), dx, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setOnPageChangeListener(OnPageChangListener onPageChangeListener){
        this.onPageChangListener=onPageChangeListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setAutoScroll(boolean autoScroll){
        this.autoScroll = autoScroll;
    }

    interface OnPageChangListener{
        public void  onPageChange(int currentPage);
    }

    interface OnClickListener{
        public void  onClick(int currentPage);
    }
}
