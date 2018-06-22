package com.aceplus.padc_poc_one.mvp.presenter;

import com.aceplus.padc_poc_one.data.model.MeditateModel;
import com.aceplus.padc_poc_one.data.vo.MainVO;
import com.aceplus.padc_poc_one.data.vo.TopicVO;
import com.aceplus.padc_poc_one.delegates.MeditateSeriesDelegate;
import com.aceplus.padc_poc_one.mvp.view.SeriesView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by kkk on 6/19/2018.
 */

public class SeriesPresenter extends BasePresenter<SeriesView> implements MeditateSeriesDelegate {

    PublishSubject<List<MainVO>> publishSubject;

    public SeriesPresenter(SeriesView mView) {
        super(mView);
        publishSubject = PublishSubject.create();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MeditateModel.getInstance().allDataLoaded(publishSubject);
        publishSubject.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MainVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MainVO> value) {
                        mView.displayPrograms(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.displayErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onCurrentItemTap() {
        mView.onLaunchDetailProgram();
    }

    @Override
    public void onListItemTap(String categoryId, String categoryItemId) {
        mView.onLaunchDetailProgram(categoryId, categoryItemId);
    }

//    @Override
//    public void onTopicItemTap(TopicVO topicVO) {
//        mView.onLaunchDetailProgram(topicVO);
//    }


}
