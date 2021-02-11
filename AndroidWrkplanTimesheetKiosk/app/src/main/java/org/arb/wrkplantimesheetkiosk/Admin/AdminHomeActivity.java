package org.arb.wrkplantimesheetkiosk.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.arb.wrkplantimesheetkiosk.Admin.EmployeeImageSettings.EmployeeImageSettingsActivity;
import org.arb.wrkplantimesheetkiosk.Admin.KioskUnitSettings.KioskUnitSettingsActivity;
import org.arb.wrkplantimesheetkiosk.Home.HomeActivity;
import org.arb.wrkplantimesheetkiosk.R;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_logout;
    LinearLayout ll_settings, ll_emp_img_settings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        tv_logout = findViewById(R.id.tv_logout);
        ll_settings = findViewById(R.id.ll_settings);
        ll_emp_img_settings = findViewById(R.id.ll_emp_img_settings);

        tv_logout.setOnClickListener(this);
        ll_settings.setOnClickListener(this);
        ll_emp_img_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.ll_settings:
                Intent intent_settings = new Intent(this, KioskUnitSettingsActivity.class);
                intent_settings.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_settings);
                break;
            case R.id.ll_emp_img_settings:
                Intent intent_image_settings = new Intent(this, EmployeeImageSettingsActivity.class);
                intent_image_settings.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent_image_settings);
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
