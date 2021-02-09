package org.arb.wrkplantimesheetkiosk.Admin.KioskUnitSettings;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.arb.wrkplantimesheetkiosk.Adapter.EmployeeImageSettingsAdapter;
import org.arb.wrkplantimesheetkiosk.Admin.EmployeeImageSettings.EmployeeImageSettingsActivity;
import org.arb.wrkplantimesheetkiosk.Config.Config;
import org.arb.wrkplantimesheetkiosk.R;
import org.json.JSONObject;
import org.json.XML;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KioskUnitSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_corp_id, ed_kios_unit_name, ed_device_id;
    TextView tv_done, tv_cancel;
    RadioGroup rg_faceattendance, rg_view_select, rg_leave_balance;
    RadioButton rdbtn_faceattndance, rdbtn_view_select, rdbtn_leave_balance;

    RadioButton rdbtn_face_attnd_yes, rdbtn_face_attnd_no, rdbtn_view_select_yes, rdbtn_view_select_no, rdbtn_leave_balance_yes, rdbtn_leave_balance_no;

    public static String faceattndance_yn = "1", view_select_yn = "1", leave_balance_yn = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk_unit_settings);
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        ed_corp_id = findViewById(R.id.ed_corp_id);
        ed_kios_unit_name = findViewById(R.id.ed_kios_unit_name);
        ed_device_id = findViewById(R.id.ed_device_id);

        rg_faceattendance = findViewById(R.id.rg_faceattendance);
        rg_view_select = findViewById(R.id.rg_view_select);
        rg_leave_balance = findViewById(R.id.rg_leave_balance);

        rdbtn_face_attnd_yes = findViewById(R.id.rdbtn_face_attnd_yes);
        rdbtn_face_attnd_no = findViewById(R.id.rdbtn_face_attnd_no);
        rdbtn_view_select_yes = findViewById(R.id.rdbtn_view_select_yes);
        rdbtn_view_select_no = findViewById(R.id.rdbtn_view_select_no);
        rdbtn_leave_balance_yes = findViewById(R.id.rdbtn_leave_balance_yes);
        rdbtn_leave_balance_no = findViewById(R.id.rdbtn_leave_balance_no);

        tv_done = findViewById(R.id.tv_done);
        tv_cancel = findViewById(R.id.tv_cancel);

        ed_device_id.setText(android_id);

        tv_done.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_done:
               validate_radiobtn();

               SaveInfo();
                Log.d("test-=>",faceattndance_yn+"\n"+view_select_yn+"\n"+leave_balance_yn);
                break;
            case R.id.tv_cancel:
                break;
        }
    }

    public void validate_radiobtn(){
        if (rdbtn_face_attnd_yes.isChecked()){
            faceattndance_yn = "1";
        }
        if (rdbtn_face_attnd_no.isChecked()){
            faceattndance_yn = "0";
        }


        if (rdbtn_view_select_yes.isChecked()){
            view_select_yn = "1";
        }
        if (rdbtn_view_select_no.isChecked()){
            view_select_yn = "0";
        }

        if (rdbtn_leave_balance_yes.isChecked()){
            leave_balance_yn = "1";
        }
        if (rdbtn_leave_balance_no.isChecked()){
            leave_balance_yn = "0";
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //---code to save info, starts-----
    public void SaveInfo(){
        String url = Config.BaseUrl + "KioskService.asmx/SaveKioskInfo";


        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait while loading data", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObj = null;
                        try{
                            jsonObj = XML.toJSONObject(response);
                            String responseData = jsonObj.toString();
                            String val = "";
                            JSONObject resobj = new JSONObject(responseData);
                            Iterator<?> keys = resobj.keys();
                            while(keys.hasNext() ) {
                                String key = (String)keys.next();
                                if ( resobj.get(key) instanceof JSONObject ) {
                                    JSONObject xx = new JSONObject(resobj.get(key).toString());
                                    val = xx.getString("content");
                                    Log.d("res1-=>",xx.getString("content"));
                                    JSONObject jsonObject = new JSONObject(val);
                                   /* String status = jsonObject.getString("status");

                                    Log.d("statusTest",status);*/

                                    if (jsonObject.getString("status").contentEquals("true")){
//                                        loadData();
                                        Log.d("result-=>",jsonObject.getString("message"));

                                    }else{
//                                        loadData();
                                        finish();
                                        startActivity(getIntent());
                                        Log.d("result-=>",jsonObject.getString("message"));
                                    }

                                    loading.dismiss();
//                                    Toast.makeText(getApplicationContext(),xx.getString("content"),Toast.LENGTH_LONG).show();
                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                            loading.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();


                String message = "Could not connect server";
               /* int color = Color.parseColor("#ffffff");
                Snackbar snackbar = Snackbar.make(findViewById(R.id.relativeLayout), message, 4000);

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(color);
                snackbar.show();*/

               /* View v = findViewById(R.id.relativeLayout);
                new org.arb.gst.config.Snackbar(message,v);
                Log.d("Volley Error-=>",error.toString());*/
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Log.d("Volley Error-=>",error.toString());

                loading.dismiss();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("CorpId", "arb-kol-dev");
                params.put("DeviceId", ed_device_id.getText().toString());
                params.put("UnitName", ed_kios_unit_name.getText().toString());
                params.put("AttendanceYn", faceattndance_yn);
                params.put("TaskListYn", view_select_yn);
                params.put("LeaveBalanceYn", leave_balance_yn);
                /*params.put("UserId", String.valueOf(RecognizeHomeActivity.PersonId));
                params.put("deviceType", "1");
                params.put("EmpType", "MAIN");*/

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //---code to save info, ends-----
}
