package com.lwd.signin.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class DialogLoading extends Dialog {

    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context);

    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }

}
