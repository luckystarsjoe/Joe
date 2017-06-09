package com.android.huimiaomiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.huimiaomiao.R;
import com.android.huimiaomiao.entity.MiaoMu;

import java.util.List;

/**
 * Created by caihaobing on 2017/5/31.
 * email：1215955877@qq.com
 */

public class MainListAdapter extends BaseListAdapter<MiaoMu> {


    private ViewHolder2 viewHolder2;

    public MainListAdapter(List<MiaoMu> miaoMuList, Context context){
        super(miaoMuList,context);
    }

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        if(convertView == null || convertView.getTag() == null){
            viewHolder2 = new ViewHolder2();
            convertView = inflater.inflate(R.layout.list_item_qgdt,parent,false);
            viewHolder2.headRlt = (RelativeLayout)convertView.findViewById(R.id.rlt_head);
//            viewHolder2.lineView = convertView.findViewById(R.id.line);
//            viewHolder2.moreTv = (TextView) convertView.findViewById(R.id.tv_more);
            convertView.setTag(viewHolder2);
        }else{
            viewHolder2 = (ViewHolder2) convertView.getTag();
        }
//        if(position != 0 ){
//            viewHolder2.lineView.setVisibility(View.GONE);
//            viewHolder2.headRlt.setVisibility(View.GONE);
//        }else{
//            viewHolder2.lineView.setVisibility(View.VISIBLE);
//            viewHolder2.headRlt.setVisibility(View.VISIBLE);
//            viewHolder2.moreTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    moreClickListner.onMoreClick();
//                }
//            });
//        }

        return convertView;
    }


    class ViewHolder2{
        RelativeLayout headRlt;
        View lineView;
        TextView nameTv,priceTv,timeTv,placeTv,moreTv;
    }

}
