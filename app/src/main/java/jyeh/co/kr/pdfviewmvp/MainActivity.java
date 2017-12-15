package jyeh.co.kr.pdfviewmvp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by jychoi on 2017. 11. 28..
 */

public class MainActivity extends Activity implements View.OnClickListener, Presenter.View {
    private ImageView mImageView;
    private Button mButtonPrevious;
    private Button mButtonNext;

    private PdfModel model;
    private PresenterImpl mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mImageView = findViewById(R.id.contents_view);
        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(this);

        mButtonPrevious = findViewById(R.id.button_prev);
        mButtonPrevious.setOnClickListener(this);

        model = new PdfModel();
        mainPresenter = new PresenterImpl(this, model);
        mainPresenter.setView(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_prev: {
                // Move to the previous page
                mainPresenter.onClickButton(false);
                break;
            }
            case R.id.button_next: {
                // Move to the next page
                mainPresenter.onClickButton(true);
                break;
            }
        }
    }
    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void setPreviousButtonEnabled(boolean isEnabled) {
        mButtonPrevious.setEnabled(isEnabled);
    }
    @Override
    public void setNextButtonEnabled(boolean isEnabled) {
        mButtonNext.setEnabled(isEnabled);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.attach();

    }

    @Override
    public void onStop() {
        mainPresenter.detach();
        super.onStop();
    }


}

