package com.android.huimiaomiao.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.huimiaomiao.R;
import com.android.huimiaomiao.utils.CountDownTimerUtils;

/**
 * Created by caihaobing on 2017/5/26.
 * email：1215955877@qq.com
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button yzmBtn;

    @Override
    protected void restoreState(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_register;
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        yzmBtn = (Button) findViewById(R.id.btn_yzm);
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void setListener() {
        yzmBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view .getId();
        switch (id){
            case R.id.btn_yzm:
                new CountDownTimerUtils(yzmBtn,60000,1000).start();
                break;
        }
    }
}
