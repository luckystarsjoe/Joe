package com.android.huimiaomiao.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by caihaobing on 2017/5/31.
 * email：1215955877@qq.com
 */

public class CountDownTimerUtils extends CountDownTimer {

    private Button button;

    public CountDownTimerUtils(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long l) {
        button.setEnabled(false);
        button.setText(l/1000 + "秒后可重新发送");
    }

    @Override
    public void onFinish() {
        button.setEnabled(true);
        button.setText("重新发送");
    }
}
