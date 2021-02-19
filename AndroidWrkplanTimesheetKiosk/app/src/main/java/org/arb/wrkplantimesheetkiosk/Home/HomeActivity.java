package org.arb.wrkplantimesheetkiosk.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.arb.wrkplantimesheetkiosk.Admin.LoginActivity;
import org.arb.wrkplantimesheetkiosk.R;
import org.arb.wrkplantimesheetkiosk.Recognize.RecognizeHomeActivity;
import org.arb.wrkplantimesheetkiosk.Recognize.RecognizeHomeRealtimeActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_rcognize;
    ImageView imgview_btn_profile;
    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_rcognize = findViewById(R.id.tv_rcognize);
        imgview_btn_profile = findViewById(R.id.imgview_btn_profile);

        tv_rcognize.setOnClickListener(this);
        imgview_btn_profile.setOnClickListener(this);

        EnableRuntimePermission();
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

    //--camera permission, starts----
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,
                Manifest.permission.CAMERA)) {
//            Toast.makeText(RecognizeHomeActivity.this,"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(HomeActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(HomeActivity.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    //--camera permission, ends----
}