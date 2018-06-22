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

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
        meditateDateAgent = MeditateDataAgentImpl.getInstance();
    }

    public static MeditateModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeditateModel();
        }
        return INSTANCE;
    }

    public void allDataLoaded(final PublishSubject<List<MainVO>> publishSubject) {
        mainVOList = new ArrayList<>();
        meditateAPI.getCurrentProgram(pageIndex, AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<GetCurrentProgramResponse, ObservableSource<GetCategoriesProgramsResponse>>() {
                    @Override
                    public ObservableSource<GetCategoriesProgramsResponse> apply(GetCurrentProgramResponse currentProgramResponse) throws Exception {
                        mainVOList.add(currentProgramResponse.getCurrentProgram());
                        return meditateAPI.getCategoriesPrograms(pageIndex, AppConstants.ACCESS_TOKEN);
                    }
                })
                .flatMap(new Function<GetCategoriesProgramsResponse, ObservableSource<GetTopicsResponse>>() {
                    @Override
                    public ObservableSource<GetTopicsResponse> apply(GetCategoriesProgramsResponse getCategoriesProgramsResponse) throws Exception {
                        mainVOList.addAll(getCategoriesProgramsResponse.getCategoriesPrograms());
                        return meditateAPI.getTopics(pageIndex, AppConstants.ACCESS_TOKEN);
                    }
                })
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
