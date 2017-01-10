package com.lwd.signin.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * User: ZRJ
 * Date: 2016/12/8
 * Email: 471564517@qq.com
 * Description:
 */
public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //首先DOWN事件，无论怎样都会传递到WebView中，这时
            //可以调用requestDisallowInterceptTouchEvent，让Scroll
            //View不拦截MOVE事件
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //在MOVE事件中，我们确定两种情况Scroll是需要ScrollView来执行
                //如果WebView中的内容滑到顶部，这时就由ScrollView来执行
                //Scroll动作。如果WebView中的内容滑到底部，这时就由
                //ScrollView来执行Scroll动作。其他情况Scroll动作都由WebView
                //来执行。
                boolean scroll = true;
                if (isTop()) {     //是否滑到顶部
                    scroll = false;
                } else if (isBottom()) {    //是否滑到底部
                    scroll = false;
                }
                getParent().getParent().requestDisallowInterceptTouchEvent(scroll);

                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.onTouchEvent(event);
    }

    private boolean isBottom() {
        float htmlHeight = getContentHeight() * getScale();
        float measuredHeight = getMeasuredHeight();
        float currentheight = getHeight() + getScrollY();
        Log.d("xuchun", htmlHeight + ", " + measuredHeight + ", " + getHeight() + ", " + getScrollY());
        return htmlHeight == currentheight;
    }

    private boolean isTop() {
        return getScrollY() == 0;
    }
}
