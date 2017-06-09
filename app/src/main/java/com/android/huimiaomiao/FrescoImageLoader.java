package com.android.huimiaomiao;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by caihaobing on 2017/5/27.
 * email：1215955877@qq.com
 */

public class FrescoImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageURI(Uri.parse((String) path));
    }

    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
