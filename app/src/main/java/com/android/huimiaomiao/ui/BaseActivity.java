package com.android.huimiaomiao.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.android.huimiaomiao.AppManager;
import com.android.huimiaomiao.utils.DialogHelp;
import com.android.huimiaomiao.utils.StringUtils;

/**
 * Created by caihaobing on 2017/5/26.
 * email：1215955877@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected Context context;
    protected LayoutInflater inflater;
    protected ProgressDialog loadingDialog;
    private boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将activity添加到栈
        AppManager.getAppManager().addActivity(this);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        context = this;
        inflater = LayoutInflater.from(this);
        isVisible = true;
        restoreState(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    protected abstract void restoreState(Bundle bundle);

    /**
     * 返回Activity布局的id
     *
     * @return
     */
    protected int getLayoutId() {
        return 0;
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 检测TextView、EditText是否为空
     *
     * @param t
     * @param <T>
     * @return
     */
    protected <T extends TextView> boolean isEmpty(T t) {
        if (StringUtils.isEmpty(getViewStr(t))) {
            return true;
        } else {
            return false;
        }
    }

    protected <T extends TextView> String getViewStr(T t) {
        return t.getText().toString().trim();
    }

    protected ProgressDialog showWaitDialog(String msg) {
        if (isVisible) {
            if (loadingDialog == null) {
                loadingDialog = DialogHelp.getWaitDialog(this, msg);
            }
            if (loadingDialog != null) {
                loadingDialog.setMessage(msg);
                loadingDialog.setCancelable(false);
                loadingDialog.show();
            }
            return loadingDialog;
        }
        return null;
    }

    protected void hideWaitDialog() {
        if (isVisible && loadingDialog != null) {
            try {
                loadingDialog.dismiss();
                loadingDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
