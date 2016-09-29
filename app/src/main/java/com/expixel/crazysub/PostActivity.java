package com.expixel.crazysub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cellbody on 2016/9/23.
 */

public class PostActivity extends BaseDialogActivity {
    @BindView(R.id.editSub)
    EditText editSub;
    @BindView(R.id.editDsc)
    EditText editDsc;
    @BindView(R.id.tvSub)
    TextView tvSub;
    @BindView(R.id.imgToday)
    ImageView imgToday;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_post);
        ButterKnife.bind(this);

        ImageLoader.getInstance().displayImage("https://firebasestorage.googleapis.com/v0/b/crazysub-a7da8.appspot.com/o/cat.jpeg?alt=media&token=62a05fb1-f1be-4511-9811-948839ec75cb", imgToday);
        editSub.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvSub.setText(editSub.getText());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }




}
