package com.android.huimiaomiao.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caihaobing on 2017/6/6.
 * email：1215955877@qq.com
 */

public abstract class BaseFragment extends Fragment {

    private View rootView;
    private Activity activity;
    private boolean isPrepared;
    private boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if(isVisibleToUser){
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser()
    {
        if (isPrepared && isVisible)
        {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad()
    {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = null;
        if (getLayoutId() != 0){
            rootView = inflater.inflate(getLayoutId(), container, false);
        }else{
            rootView = super.onCreateView(inflater,container,savedInstanceState);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();//避免重复添加的问题
        if (parent != null) {
            parent.removeView(rootView);
        }
        isPrepared = true;
        initView(rootView,savedInstanceState);
        initData();
        setListener();
        onLazyLoad();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initView(View view,Bundle bundle);

    protected abstract void initData();

    protected abstract void setListener();

    /**
     * 获取资源id
     * @return
     */
    protected abstract int getLayoutId();

    protected <T extends View> T findViewById(int id){
        if(rootView == null){
            return null;
        }
        return (T) rootView.findViewById(id);
    }
}
