package com.android.huimiaomiao.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.huimiaomiao.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by caihaobing on 2017/5/27.
 * email：1215955877@qq.com
 */

public class MineFragment extends Fragment {
    private SimpleDraweeView simpleDraweeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine,container,false);
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_face);
        simpleDraweeView.setImageURI(Uri.parse("http://scimg.jb51.net/touxiang/201705/201705232047503.jpg"));
        return view;
    }

    public String getFragmentName(){
        return IndexFragment.class.getName();
    }
}
