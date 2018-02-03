package com.kfy.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by alan on 2018/2/2.
 */

public class CustomDialog extends Dialog {
    private static final String TAG = CustomDialog.class.getSimpleName();
    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;

    private CustomDialog(@NonNull Builder builder) {
        super(builder.context);
        init(builder);
    }

    private CustomDialog(@NonNull Builder builder, int themeResId) {
        super(builder.context, themeResId);
        init(builder);
    }

    private void init(Builder builder) {
        this.context = builder.context;
        this.height = builder.height;
        this.width = builder.width;
        this.cancelTouchout = builder.cancelTouchout;
        this.view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = width;
        lp.height = height;
        lp.gravity = Gravity.CENTER;
        win.setAttributes(lp);
    }

    public void addViewOnClick(int viewId, View.OnClickListener listener) {
        this.view.findViewById(viewId).setOnClickListener(listener);
    }
    public static final class Builder {
        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int viewResId) {
            this.view = LayoutInflater.from(context).inflate(viewResId, null);
            Log.d(TAG, "width : " + view.getWidth() +
                    "height : " + view.getHeight());
            return this;
        }

        public Builder heightx(int heightPx) {
            this.height = heightPx;
            return this;
        }

        public Builder widthPx(int widthPx) {
            this.width = widthPx;
            return this;
        }

        public Builder heightDp(int heightDp) {
            this.height = dp2px(context, heightDp);
            return this;
        }

        public Builder widthDp(int widthDp) {
            this.width = dp2px(context, widthDp);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            this.height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            this.width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            this.cancelTouchout = val;
            return this;
        }

        /**
         * todo 在Builder模式中添加点击事件时，如果操作此时正在构建的CustomDialog会编译器会检查到可能未初始化，
         * 所以如果要在点击事件中操作该CustomDialog则不能通过该方法添加点击事件
         * @param viewId
         * @param listener
         * @return
         */
        public Builder addViewOnClick(int viewId, View.OnClickListener listener) {
            this.view.findViewById(viewId).setOnClickListener(listener);
            return this;
        }

        public CustomDialog build() {
            if (resStyle != -1) {
                return new CustomDialog(this, resStyle);
            } else {
                return new CustomDialog(this);
            }
        }
    }


    private static int dp2px(Context context, int dpValue) {
        return (int) (context.getResources().getDisplayMetrics().density * dpValue + 0.5);
    }

}
