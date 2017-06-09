package com.android.huimiaomiao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.huimiaomiao.R;
import com.android.huimiaomiao.view.LoadStateLayout;

/**
 * Created by caihaobing on 2017/5/27.
 * email：1215955877@qq.com
 */

public class CartFragment extends Fragment {

    private LoadStateLayout stateLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cart,container,false);
        stateLayout = (LoadStateLayout) view.findViewById(R.id.load_state_layout);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stateLayout.setNoDataContent("想要的这么多，购物车却是空空的...");
                stateLayout.setErrorType(LoadStateLayout.NODATA);
                stateLayout.setErrorImag(R.drawable.app_icon);
            }
        },2000);
        return view;
    }

    private Handler handler = new Handler();
}
