package com.aceplus.padc_poc_one.mvp.presenter;

import com.aceplus.padc_poc_one.data.model.MeditateModel;
import com.aceplus.padc_poc_one.data.vo.CategoriesProgramsItemVO;
import com.aceplus.padc_poc_one.data.vo.CurrentProgramVO;
import com.aceplus.padc_poc_one.mvp.view.DetailProgramView;

/**
 * Created by kkk on 6/19/2018.
 */

public class DetailProgramPresenter extends BasePresenter<DetailProgramView> {
    private MeditateModel meditateModel;
    private static String IE_CURRENT_VO = "currentVO";
    private static String IE_CATEGORY_VO = "categoriesVO";

    public DetailProgramPresenter(DetailProgramView mView) {
        super(mView);
        meditateModel = MeditateModel.getInstance();
    }


    public void onFinishUIComponentSetup(String vo, String categoryId, String categoryItemId) {
        if (vo.equals(IE_CATEGORY_VO)) {
            CategoriesProgramsItemVO programsItemVO = meditateModel.getCategoriesProgramsItemVO(categoryId, categoryItemId);
            mView.displayCategoryProgramDetail(programsItemVO);

        } else if (vo.equals(IE_CURRENT_VO)) {
            CurrentProgramVO currentProgramVO = meditateModel.getCurrentProgramVO();
            mView.displayCurrentProgramDetail(currentProgramVO);

        }
    }
}
