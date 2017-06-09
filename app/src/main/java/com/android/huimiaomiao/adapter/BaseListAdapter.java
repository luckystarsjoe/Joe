package com.android.huimiaomiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.huimiaomiao.R;
import com.android.huimiaomiao.utils.NetUtils;

import java.util.List;

/**
 * Created by caihaobing on 2017/6/1.
 * email：1215955877@qq.com
 */

public class BaseListAdapter<T> extends BaseAdapter {

    public static final int STATE_EMPTY = 0;//暂无数据
    public static final int STATE_LOAD_MORE = 1;//加载更多
    public static final int STATE_NO_MORE = 2;//没有更多数据
    public static final int STATE_LESS_ONE_PAGE = 3;//不足一页
    public static final int STATE_NETWORK_ERROR = 4;//网络异常

    protected Context context;
    protected List<T> list;
    protected LayoutInflater inflater;
    protected LinearLayout mFooterView;

    protected int state;

    public BaseListAdapter(List<T> list,Context context){
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public BaseListAdapter() {

    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return this.state;
    }

    @Override
    public int getCount() {
        switch (getState()){
            case STATE_EMPTY:
            case STATE_LOAD_MORE:
            case STATE_NO_MORE:
            case STATE_NETWORK_ERROR:
                return getDataSizePlus1();
            case STATE_LESS_ONE_PAGE:
                return list.size();
            default:
                break;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(list.size() > i){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(position == getCount() - 1 && hasFooterView()){
            if(state == STATE_EMPTY || state == STATE_LESS_ONE_PAGE || state == STATE_LOAD_MORE || state == STATE_NETWORK_ERROR
                    || state == STATE_NO_MORE){
                this.mFooterView = (LinearLayout) inflater.inflate(R.layout.list_item_footer,null,false);
                ProgressBar progressBar = (ProgressBar) mFooterView.findViewById(R.id.progressbar);
                TextView stateTv = (TextView)mFooterView.findViewById(R.id.text);
                switch (getState()) {
                    case STATE_LOAD_MORE:
                        mFooterView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        stateTv.setText(R.string.loading);
                        break;
                    case STATE_NO_MORE:
                        mFooterView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        stateTv.setText(R.string.no_more);
                        break;
                    case STATE_EMPTY:
                        progressBar.setVisibility(View.GONE);
                        mFooterView.setVisibility(View.VISIBLE);
                        stateTv.setText(R.string.empty);
                        break;
                    case STATE_NETWORK_ERROR:
                        mFooterView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        if (NetUtils.isConnected(context)) {
                            stateTv.setText(R.string.server_error);
                        } else {
                            stateTv.setText(R.string.network_error);
                        }
                        break;
                    default:
                        mFooterView.setVisibility(View.GONE);
                        break;
                }
                return mFooterView;
            }
        }
        if (position < 0) {
            position = 0; // 若列表没有数据，是没有footview/headview的
        }
        return getRealView(position, view, viewGroup);
    }

    protected View getRealView(int position,View convertView,ViewGroup parent){
        return null;
    }

    protected boolean  hasFooterView(){
        return true;
    }


    public int getDataSizePlus1(){
        if(hasFooterView()){
            return list.size() + 1;
        }
        return list.size();
    }
}
