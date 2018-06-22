package com.aceplus.padc_poc_one.mvp.view;

import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsItemVO;
import com.aceplus.padc_poc_one.data.vo.CurrentProgramVO;

/**
 * Created by kkk on 6/19/2018.
 */

public interface DetailProgramView extends BaseView {

    void displayCurrentProgramDetail(CurrentProgramVO currentProgramVO);

    void displayCategoryProgramDetail(CategoriesProgramsItemVO categoriesProgramsItemVO);

}
