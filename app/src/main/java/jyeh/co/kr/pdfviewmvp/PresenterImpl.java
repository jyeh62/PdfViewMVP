package jyeh.co.kr.pdfviewmvp;

import android.app.Activity;
import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by jychoi on 2017. 11. 28..
 */

public class PresenterImpl implements Presenter {
    private Activity activity;
    private Presenter.View view;
    private PdfModel model;

    public PresenterImpl(Activity activity, PdfModel model) {
        this.activity = activity;
        this.model = model;
    }

    @Override
    public void showPage(int index) {
        Bitmap bitmap = model.getCurrentPage(index);
        view.setImageBitmap(bitmap);
        updateUi();
    }

    @Override
    public void onClickButton(boolean isNext) {
        int index = model.getIndex();
        if (isNext) {
            showPage(index + 1);
        } else {
            showPage(index - 1);
        }
    }
    private void updateUi() {
        int index = model.getIndex();
        int pageCount = model.getPageCount();
        view.setPreviousButtonEnabled(0 != index);
        view.setNextButtonEnabled(index + 1 < pageCount);
        view.setTitle(activity.getString(R.string.app_name_with_index, index + 1, pageCount));
    }

    @Override
    public void setView(Presenter.View view) {
        this.view = view;
    }

    @Override
    public void attach() {
        model.openPdfFile(activity.getBaseContext());
        showPage(0);
    }

    @Override
    public void detach() {
        try {
            model.closeRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
