package com.android.huimiaomiao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.huimiaomiao.FrescoImageLoader;
import com.android.huimiaomiao.R;
import com.android.huimiaomiao.adapter.BaseListAdapter;
import com.android.huimiaomiao.adapter.MainListAdapter;
import com.android.huimiaomiao.entity.MiaoMu;
import com.android.huimiaomiao.ui.QiuGouDaTingActivity;
import com.android.huimiaomiao.utils.DisplayUtil;
import com.android.huimiaomiao.view.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caihaobing on 2017/5/27.
 * email：1215955877@qq.com
 */

public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private Banner banner;
    private RefreshLayout mSwipeRefreshLayout;
    private ListView listView;
    private MainListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_index,container,false);
        listView = (ListView) view.findViewById(R.id.lv_main);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.main_banner,null);

        banner = (Banner) linearLayout.findViewById(R.id.banner);
        banner.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(getActivity(),150)));
        //设置图片加载器
        banner.setImageLoader(new FrescoImageLoader());
        //设置图片集合
        banner.setImages(getImageUrls());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setBannerTitles(getTitles());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
        listView.addHeaderView(banner);
        listView.addHeaderView(inflater.inflate(R.layout.main_mmpt,null,false));
        View listHeadView = inflater.inflate(R.layout.main_list_head,null,false);
        listHeadView.findViewById(R.id.tv_more).setOnClickListener(this);
        listView.addHeaderView(listHeadView);

        adapter = new MainListAdapter(getMiaoMuList(),getActivity());
        adapter.setState(BaseListAdapter.STATE_LOAD_MORE);
        listView.setAdapter(adapter);

        mSwipeRefreshLayout = (RefreshLayout) view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();

    }

    private List<String> getImageUrls(){
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496477529&di=e4e4b4233071828effc2cb928d9e5a4a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.taopic.com%2F150704%2F240499-150F40QS335.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495882775734&di=6405a223c420cff33e9329c8ebbbd4ac&imgtype=0&src=http%3A%2F%2Fimg.bzcm.net%2Fnews%2Fattachement%2Fjpg%2Fsite2%2F20141103%2F0003ffa94ec915c114ff11.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495882775733&di=7772ad53a91d967d3d2d5d0ea28e6b0a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F7%2F548123fbd3b5f.jpg");
        return list;
    }

    private List<String> getTitles(){
        List<String> list = new ArrayList<>();
        list.add("您好，王老板");
        list.add("习近平到访天立泰");
        list.add("王俊飞亲自接待");
        return list;
    }

    private List<MiaoMu> getMiaoMuList(){
        List<MiaoMu> miaoMus = new ArrayList<>();
        for(int i= 0;i<15;i++){
            MiaoMu miaoMu = new MiaoMu();
            miaoMus.add(miaoMu);
        }
        return miaoMus;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_more:
                startActivity(new Intent(getActivity(),QiuGouDaTingActivity.class));
                break;
            default:
                break;
        }
    }
}
