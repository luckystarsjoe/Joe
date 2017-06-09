package com.android.huimiaomiao.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.huimiaomiao.R;
import com.android.huimiaomiao.adapter.BaseListAdapter;
import com.android.huimiaomiao.adapter.MainListAdapter;
import com.android.huimiaomiao.entity.MiaoMu;
import com.android.huimiaomiao.utils.DisplayUtil;
import com.android.huimiaomiao.view.LoadStateLayout;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caihaobing on 2017/6/2.
 * email：1215955877@qq.com
 */

public class QiuGouDaTingActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private LoadStateLayout stateLayout;

    private MainListAdapter adapter;

    private TextView refreshTipTv;

    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.act_qgdt;
    }

    @Override
    protected void restoreState(Bundle bundle) {

    }

    @Override
    protected void initView() {
        refreshTipTv = (TextView) findViewById(R.id.tv_refresh_tip);
        listView = (ListView) findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        stateLayout = (LoadStateLayout) findViewById(R.id.state_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = new MainListAdapter(getData(),context);
                listView.setAdapter(adapter);
                stateLayout.setErrorType(LoadStateLayout.HIDE_LAYOUT);
                adapter.setState(BaseListAdapter.STATE_NO_MORE);
            }
        },2000);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshTipTv.setVisibility(View.VISIBLE);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(refreshTipTv, "translationY", -refreshTipTv.getHeight(), 0),
                        ObjectAnimator.ofFloat(refreshTipTv, "alpha", 0, 1f)
                );
                set.setDuration(150).start();
                AnimatorSet set1 = new AnimatorSet();
                set1.playTogether(
                        ObjectAnimator.ofFloat(refreshTipTv, "translationY", 0,-refreshTipTv.getHeight() ),
                        ObjectAnimator.ofFloat(refreshTipTv, "alpha", 1, 0f)
                );
                set1.setDuration(150).setStartDelay(2000);
                set1.start();
                swipeRefreshLayout.setRefreshing(false);
            }
        },1000);
    }

    private List<MiaoMu> getData(){
        List<MiaoMu> miaoMus = new ArrayList<>();
        for(int i = 0;i<10;i++){
            MiaoMu miaoMu = new MiaoMu();
            miaoMus.add(miaoMu);
        }
        return miaoMus;
    }
}
