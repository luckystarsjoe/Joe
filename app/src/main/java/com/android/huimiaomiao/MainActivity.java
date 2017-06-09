package com.android.huimiaomiao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.huimiaomiao.fragment.CartFragment;
import com.android.huimiaomiao.fragment.IndexFragment;
import com.android.huimiaomiao.fragment.MineFragment;
import com.android.huimiaomiao.ui.BaseActivity;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    private int lastSelectedPosition;

    private Fragment mCurrentFragment,indexFragment,cartFragment,mineFragment;
    private FragmentManager fragmentManager;

    public static final String[] FRAGMENT_TAG = {"indexFragment","cartFragment","mineFragment"};

    private TextView titleTv;

    private Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void restoreState(Bundle bundle) {
        fragmentManager = getSupportFragmentManager();
        if(bundle != null){
            lastSelectedPosition = bundle.getInt("position",0);
            indexFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            cartFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            mineFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
        }
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTv = (TextView) findViewById(R.id.tv_title);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_main_sel, "首页").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.tab_cart_sel1, "购物车").setActiveColor(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.tab_me_sel, "我的").setActiveColor(R.color.colorPrimary))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();
    }

    @Override
    protected void initData() {
        switchContent(lastSelectedPosition);
    }

    @Override
    protected void setListener() {
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position",lastSelectedPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onTabSelected(int position) {
        switchContent(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 切换fragment
     * @param index
     */
    public void switchContent(int index) {
        hide();
        lastSelectedPosition = index;
        toolbar.setVisibility(View.VISIBLE);
        switch (index){
            case 0:
                titleTv.setText("首页");
                if(indexFragment == null){
                    indexFragment = new IndexFragment();
                }
                if(mCurrentFragment != indexFragment){
                    if(indexFragment.isAdded()){
                        fragmentManager.beginTransaction().show(indexFragment).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container,indexFragment,FRAGMENT_TAG[0]).commit();
                    }
                    mCurrentFragment = indexFragment;
                }
                break;
            case 1:
                titleTv.setText("购物车");
                if(cartFragment == null){
                    cartFragment = new CartFragment();
                }
                if(mCurrentFragment != cartFragment){
                    if(cartFragment.isAdded()){
                        fragmentManager.beginTransaction().show(cartFragment).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container,cartFragment,FRAGMENT_TAG[1]).commit();
                    }
                    mCurrentFragment = cartFragment;
                }
                break;
            case 2:
                toolbar.setVisibility(View.GONE);
                titleTv.setText("我的");
                if(mineFragment == null){
                    mineFragment = new MineFragment();
                }
                if(mCurrentFragment != mineFragment){
                    if(mineFragment.isAdded()){
                        fragmentManager.beginTransaction().show(mineFragment).commit();
                    }else{
                        fragmentManager.beginTransaction().add(R.id.container,mineFragment,FRAGMENT_TAG[2]).commit();
                    }
                    mCurrentFragment = mineFragment;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 影藏已添加的fragment
     */
    private void hide(){
        if(indexFragment != null){
            fragmentManager.beginTransaction().hide(indexFragment).commit();
        }
        if(cartFragment != null){
            fragmentManager.beginTransaction().hide(cartFragment).commit();
        }
        if(mineFragment != null){
            fragmentManager.beginTransaction().hide(mineFragment).commit();
        }
    }
}
