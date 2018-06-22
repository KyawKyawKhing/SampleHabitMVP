package com.aceplus.padc_poc_one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.aceplus.padc_poc_one.R;
import com.aceplus.padc_poc_one.adapter.SessionAdapter;
import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsItemVO;
import com.aceplus.padc_poc_one.data.vo.CurrentProgramVO;
import com.aceplus.padc_poc_one.mvp.presenter.DetailProgramPresenter;
import com.aceplus.padc_poc_one.mvp.view.DetailProgramView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailShowActivity extends AppCompatActivity implements DetailProgramView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.description)
    TextView tvDescription;

    @BindView(R.id.rv_session)
    RecyclerView rvSession;

    @BindView(R.id.session_title)
    TextView sessionTitle;

    SessionAdapter sessionAdapter;
    DetailProgramPresenter mPresenter;

    private static String IE_VO = "vo";
    private static String IE_CATEGORY_ID = "categoryId";
    private static String IE_CATEGORY_ITEM_ID = "categoryItemId";
    private static String IE_CURRENT_VO = "currentVO";
    private static String IE_CATEGORY_VO = "categoriesVO";
    private static String IE_TOPIC_VO = "topicVO";
    private static String IE_TOPIC_NAME = "topicName";
    private static String IE_TOPIC_DESP = "topicDesp";

    public static Intent newIntentCurrentProgram(Context context) {
        Intent intent = new Intent(context, DetailShowActivity.class);
        intent.putExtra(IE_VO, IE_CURRENT_VO);
        return intent;
    }

    public static Intent newIntentProgramInCategoryId(Context context, String categoryId, String categoryItemId) {
        Intent intent = new Intent(context, DetailShowActivity.class);
        intent.putExtra(IE_VO, IE_CATEGORY_VO);
        intent.putExtra(IE_CATEGORY_ID, categoryId);
        intent.putExtra(IE_CATEGORY_ITEM_ID, categoryItemId);
        return intent;
    }

//    public static Intent newIntentTopic(Context context, String topicName, String topicDesc) {
//        Intent intent = new Intent(context, DetailShowActivity.class);
//        intent.putExtra(IE_VO, IE_TOPIC_VO);
//        intent.putExtra(IE_TOPIC_NAME, topicName);
//        intent.putExtra(IE_TOPIC_DESP, topicDesc);
//        return intent;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionAdapter = new SessionAdapter(getApplicationContext());
        rvSession.setAdapter(sessionAdapter);
        rvSession.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new DetailProgramPresenter(this);
        mPresenter.onFinishUIComponentSetup(getIntent().getStringExtra(IE_VO), getIntent().getStringExtra(IE_CATEGORY_ID), getIntent().getStringExtra(IE_CATEGORY_ITEM_ID));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void displayCurrentProgramDetail(CurrentProgramVO currentProgramVO) {
        getSupportActionBar().setTitle(currentProgramVO.getTitle());
        tvDescription.setText(currentProgramVO.getDescription());
        if (sessionAdapter.getItemCount() == 0) {
            sessionTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayCategoryProgramDetail(CategoriesProgramsItemVO categoriesProgramsItemVO) {
        getSupportActionBar().setTitle(categoriesProgramsItemVO.getTitle());
        tvDescription.setText(categoriesProgramsItemVO.getDescription());
        sessionAdapter.setNewData(categoriesProgramsItemVO.getSessionsVOS());
        if (sessionAdapter.getItemCount() == 0) {
            sessionTitle.setVisibility(View.GONE);
        }
    }


}
