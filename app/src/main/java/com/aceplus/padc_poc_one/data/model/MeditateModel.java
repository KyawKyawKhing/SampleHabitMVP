package com.aceplus.padc_poc_one.data.model;

import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsItemVO;
import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsVO;
import com.aceplus.padc_poc_one.data.vo.CurrentProgramVO;
import com.aceplus.padc_poc_one.data.vo.MainVO;
import com.aceplus.padc_poc_one.network.MeditateDataAgentImpl;
import com.aceplus.padc_poc_one.network.MeditateDateAgent;
import com.aceplus.padc_poc_one.network.reponses.GetCategoriesProgramsResponse;
import com.aceplus.padc_poc_one.network.reponses.GetCurrentProgramResponse;
import com.aceplus.padc_poc_one.network.reponses.GetTopicsResponse;
import com.aceplus.padc_poc_one.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by kkk on 5/26/2018.
 */

public class MeditateModel extends BaseModel {

    private static MeditateModel INSTANCE;
    private List<MainVO> mainVOList;
    private int pageIndex = 1;
    private MeditateDateAgent meditateDateAgent;


    private MeditateModel() {
//        EventBus.getDefault().register(this);
        mainVOList = new ArrayList<>();
        meditateDateAgent = MeditateDataAgentImpl.getInstance();
    }

    public static MeditateModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeditateModel();
        }
        return INSTANCE;
    }

    public void allDataLoaded(final PublishSubject<List<MainVO>> publishSubject) {
//        meditateDateAgent.loadCurrentProgram(pageIndex, AppConstants.ACCESS_TOKEN);
        meditateAPI.getCurrentProgram(pageIndex, AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<GetCurrentProgramResponse>() {
                    @Override
                    public void accept(GetCurrentProgramResponse getCurrentProgramResponse) throws Exception {
                        mainVOList.add(getCurrentProgramResponse.getCurrentProgram());
                        meditateAPI.getCategoriesPrograms(pageIndex, AppConstants.ACCESS_TOKEN)
                                .subscribeOn(Schedulers.io())
                                .doOnNext(new Consumer<GetCategoriesProgramsResponse>() {
                                    @Override
                                    public void accept(GetCategoriesProgramsResponse getCategoriesProgramsResponse) throws Exception {
                                        mainVOList.addAll(getCategoriesProgramsResponse.getCategoriesPrograms());
                                        meditateAPI.getTopics(pageIndex, AppConstants.ACCESS_TOKEN)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Observer<GetTopicsResponse>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {

                                                    }

                                                    @Override
                                                    public void onNext(GetTopicsResponse value) {
                                                        mainVOList.addAll(value.getTopics());
                                                        publishSubject.onNext(mainVOList);
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onComplete() {

                                                    }
                                                });
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
//                .flatMap()
//                .doOnSuccess(dto1 -> {
//
//                })
//                .flatMap(dto1 -> api.getDto2())
//                .doOnSuccess(dto2 -> {/*do something with dto2*/})
//                .flatMap(dto2 -> api.getDto3())
//                .doOnSuccess(dto3 -> {/*do something with dto3*/})
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
//        meditateAPI.getCurrentProgram(pageIndex, AppConstants.ACCESS_TOKEN)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GetCurrentProgramResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GetCurrentProgramResponse value) {
//                        mainVOList.add(value.getCurrentProgram());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        meditateAPI.getCategoriesPrograms(pageIndex, AppConstants.ACCESS_TOKEN)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GetCategoriesProgramsResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GetCategoriesProgramsResponse value) {
//                        mainVOList.addAll(value.getCategoriesPrograms());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        meditateAPI.getTopics(pageIndex, AppConstants.ACCESS_TOKEN)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GetTopicsResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GetTopicsResponse value) {
//                        mainVOList.addAll(value.getTopics());
//                        publishSubject.onNext(mainVOList);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onCurrentProgramLoaded(RestApiEvents.CurrentProgramDataLoadedEvent event) {
//        mainVOList.add(event.getCurrentProgramVO());
//        Log.e("mainvo list size", mainVOList.size() + "");
//        meditateDateAgent.loadCategoriesPrograms(pageIndex, AppConstants.ACCESS_TOKEN);
//    }
//
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onCategoriesProgramsDataLoaded(RestApiEvents.CategoriesProgramsDataLoadedEvent event) {
//        mainVOList.addAll(event.getCategoriesProgramsVOList());
//        Log.e("mainvo list size", mainVOList.size() + "");
//        mainVOList.add(new SingleVO("All Topics"));
//        Log.e("mainvo list size", mainVOList.size() + "");
//        meditateDateAgent.loadTopics(pageIndex, AppConstants.ACCESS_TOKEN);
//    }
//
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onTopicsDataLoaded(RestApiEvents.TopicsDataLoadedEvent event) {
//        mainVOList.addAll(event.getTopicVOList());
//        Log.e("mainvo list size", mainVOList.size() + "");
//        RestApiEvents.AllDataLoadedEvent allDataLoadedEvent = new RestApiEvents.AllDataLoadedEvent(mainVOList);
//        EventBus.getDefault().post(allDataLoadedEvent);
//    }

    public List<MainVO> getMainVOList() {
        if (mainVOList.isEmpty()) {
            mainVOList = new ArrayList<>();
        }
        return mainVOList;
    }

    public CurrentProgramVO getCurrentProgramVO() {
        for (MainVO mainVO : mainVOList) {
            if (mainVO instanceof CurrentProgramVO) {
                return (CurrentProgramVO) mainVO;
            }
        }
        return null;
    }

    public CategoriesProgramsItemVO getCategoriesProgramsItemVO(String categoryId, String categoryItemId) {
        for (MainVO mainVO : mainVOList) {
            if (mainVO instanceof CategoriesProgramsVO) {
                if (((CategoriesProgramsVO) mainVO).getCategoryId().equals(categoryId)) {
                    for (CategoriesProgramsItemVO categoriesProgramsItemVO : ((CategoriesProgramsVO) mainVO).getCategoriesProgramsItemVOS()) {
                        if (categoriesProgramsItemVO.getProgramId().equals(categoryItemId)) {
                            return categoriesProgramsItemVO;
                        }
                    }
                }
            }
        }
        return null;
    }
}
