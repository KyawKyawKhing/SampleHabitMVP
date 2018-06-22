package com.aceplus.padc_poc_one.mvp.view;

import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsVO;
import com.aceplus.padc_poc_one.data.vo.CurrentProgramVO;
import com.aceplus.padc_poc_one.data.vo.MainVO;
import com.aceplus.padc_poc_one.data.vo.TopicVO;

import java.util.List;

/**
 * Created by kkk on 6/19/2018.
 */

public interface SeriesView extends BaseView {

    void displayPrograms(List<MainVO> mainVOS);

    void onLaunchDetailProgram();

    void onLaunchDetailProgram(String categoryId, String categoryItemId);

//    void onLaunchDetailProgram(String id);

//    void displayCurrentProgram(CurrentProgramVO currentProgramVO);
//
//    void displayCategories(CategoriesProgramsVO categoriesProgramsVO);
//
//    void displayTopics(TopicVO topicVO);
}
