package com.android.huimiaomiao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.huimiaomiao.MainActivity;
import com.android.huimiaomiao.R;

/**
 * Created by caihaobing on 2017/5/26.
 * email：1215955877@qq.com
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView regTv;
    private Button loginBtn;

    @Override
    protected void restoreState(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    protected void initView() {
        regTv =  (TextView) findViewById(R.id.tv_reg_now);
        loginBtn = (Button)findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        regTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view .getId();
        switch (id){
            case R.id.tv_reg_now :
                readyGo(RegisterActivity.class);
                break;
            case R.id.btn_login:
                readyGoThenKill(MainActivity.class);
            default:
                break;
        }
    }
}
