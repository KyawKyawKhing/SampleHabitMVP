package com.aceplus.padc_poc_one.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aceplus.padc_poc_one.R;
import com.aceplus.padc_poc_one.adapter.RecyclerViewAdapter;
import com.aceplus.padc_poc_one.data.vo.MainVO;
import com.aceplus.padc_poc_one.data.vo.TopicVO;
import com.aceplus.padc_poc_one.mvp.presenter.SeriesPresenter;
import com.aceplus.padc_poc_one.mvp.view.SeriesView;
import com.aceplus.padc_poc_one.ui.activity.DetailShowActivity;

import java.util.List;


public class MeditateSeriesFragment extends Fragment implements SeriesView {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    SeriesPresenter mPresenter;

    public MeditateSeriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SeriesPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meditate_series, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(getContext(), mPresenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//        adapter.setNewList(MeditateModel.getInstance().getMainVOList());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAllDataLoaded(RestApiEvents.AllDataLoadedEvent event) {
//        adapter.setNewList(event.getMainVOList());
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onErrorDataLoaded(RestApiEvents.ErrorInvokingAPIEvent event) {
//        Toast.makeText(getContext(), event.getErrorMsg(), Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof MeditateSeriesDelegate) {
//            meditateSeriesDelegate = (MeditateSeriesDelegate) context;
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        meditateSeriesDelegate = null;
//    }

    @Override
    public void displayPrograms(List<MainVO> mainVOS) {
        adapter.setNewList(mainVOS);
    }

    @Override
    public void onLaunchDetailProgram() {
        Intent intent = DetailShowActivity.newIntentCurrentProgram(getContext());
        startActivity(intent);
    }

    @Override
    public void onLaunchDetailProgram(String categoryId, String categoryItemId) {
        Intent intent = DetailShowActivity.newIntentProgramInCategoryId(getContext(), categoryId, categoryItemId);
        startActivity(intent);
    }

//    @Override
//    public void onLaunchDetailProgram(TopicVO topicVO) {
//        Intent intent = DetailShowActivity.newIntentTopic(getContext(), topicVO.getTopicName(), topicVO.getTopicDesc());
//        startActivity(intent);
//    }

    @Override
    public void displayErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
