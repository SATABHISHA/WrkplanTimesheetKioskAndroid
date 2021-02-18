package org.arb.wrkplantimesheetkiosk.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.arb.wrkplantimesheetkiosk.Admin.LoginActivity;
import org.arb.wrkplantimesheetkiosk.R;
import org.arb.wrkplantimesheetkiosk.Recognize.RecognizeHomeActivity;
import org.arb.wrkplantimesheetkiosk.Recognize.RecognizeHomeRealtimeActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_rcognize;
    ImageView imgview_btn_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_rcognize = findViewById(R.id.tv_rcognize);
        imgview_btn_profile = findViewById(R.id.imgview_btn_profile);

        tv_rcognize.setOnClickListener(this);
        imgview_btn_profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_rcognize:
                startActivity(new Intent(this, RecognizeHomeRealtimeActivity.class));
                break;
            case R.id.imgview_btn_profile:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
    }
}