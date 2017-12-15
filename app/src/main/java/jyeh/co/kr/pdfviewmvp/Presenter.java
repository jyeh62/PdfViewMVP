package jyeh.co.kr.pdfviewmvp;

import android.graphics.Bitmap;

/**
 * Created by jychoi on 2017. 11. 28..
 */

public interface Presenter {

    interface View {
        void setTitle(CharSequence title);
        void setImageBitmap(Bitmap bitmap);
        void setPreviousButtonEnabled(boolean isEnabled);
        void setNextButtonEnabled(boolean isEnabled);
    }

    void setView(Presenter.View view);

    void showPage(int index);
    void onClickButton(boolean isNext);

    void attach();
    void detach();
}
