package com.kfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kfy.widget.CustomDialog;

public class MainActivity extends AppCompatActivity {

    private CustomDialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void createDialog(View v) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        customDialog = builder
                .view(R.layout.dialog_demo)
                .style(R.style.Dialog)
                .addViewOnClick(R.id.dialog_btn_confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDialog.dismiss();
                    }
                })
                .heightDimenRes(R.dimen.dialog_unify_height)
                .widthDimenRes(R.dimen.dialog_unify_width)
                .cancelTouchout(false)
                .build();

        customDialog.show();
    }

}
