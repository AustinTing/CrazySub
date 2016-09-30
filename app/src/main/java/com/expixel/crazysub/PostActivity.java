package com.expixel.crazysub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.btnPostOk)
    Button btnPostOk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_post);
        ButterKnife.bind(this);
        dbRef.child("image").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d(TAG, "PostActivity: data: "+data.getValue());
                    Image image = data.getValue(Image.class);
                    ImageLoader.getInstance().displayImage(image.url, imgToday);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "PostActivity: onCancelled: " + databaseError.getDetails());

            }
        });


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


    @OnClick(R.id.btnPostOk)
    public void onClick() {
        Log.d(TAG, "PostActivity: btnPostOk onClick: ");
        String userUrl = auth.getCurrentUser().getPhotoUrl().toString();
        String sub = editSub.getText().toString();
        String dsc = editDsc.getText().toString();

        if(sub.equals("")){
            Toast.makeText(this, "忘記加你最狂的字幕啦RRRRRR", Toast.LENGTH_SHORT).show();
        }else if(!FastClickSensor.isFastDoubleClick()){
            Post post = new Post(userUrl,System.currentTimeMillis(), sub, dsc, 0, 0);
            String key = dbRef.child("posts").push().getKey();
            String uid = auth.getCurrentUser().getUid();
            dbRef.child("post").child(key).setValue(post);
            dbRef.child("user-post").child(uid).child(key).setValue(post);
            this.finish();
        }

    }
}
