package com.aceplus.padc_poc_one.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aceplus.padc_poc_one.R;
import com.aceplus.padc_poc_one.data.vo.TopicVO;
import com.aceplus.padc_poc_one.delegates.MeditateSeriesDelegate;
import com.bumptech.glide.Glide;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by kkk on 5/19/2018.
 */

public class TopicsViewHolder extends BaseViewHolder<TopicVO> implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_desp)
    TextView tv_desp;
    @BindView(R.id.iv_main_bg)
    ImageView iv_bg;
    @BindView(R.id.iv_content)
    ImageView iv_item;
    private MeditateSeriesDelegate delegate;
    TopicVO topicVO;
    private int[] photoList = {R.mipmap.rv_ver_land8, R.mipmap.rv_ver_land6, R.mipmap.rv_ver_land4};

    public TopicsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(TopicVO data) {
        topicVO = data;
        Random random = new Random();
        Context context = this.itemView.getContext();
        tv_title.setText(data.getTopicName());
        tv_desp.setText(data.getTopicDesc());
        Glide.with(context).load(photoList[random.nextInt(photoList.length - 1)]).into(iv_bg);
        itemView.setOnClickListener(this);
//        iv_bg.setImageDrawable(context.getResources().getDrawable(photoList[random.nextInt(photoList.length - 1)]));
    }

    @Override
    public void onClick(View v) {
//        delegate.onTopicItemTap(topicVO);
    }
}
