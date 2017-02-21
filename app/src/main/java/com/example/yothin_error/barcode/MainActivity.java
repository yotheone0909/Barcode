package com.example.yothin_error.barcode;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.client.android.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOk;
    EditText etBarCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = (Button) findViewById(R.id.Ok);
        etBarCode = (EditText)findViewById(R.id.etBarCode);

        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (etBarCode.getText().toString().isEmpty()) {
            if (v == btnOk) {
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                intent.setAction("com.google.zxing.client.android.SCAN");
                intent.putExtra("SAVE_HISTORY", false);
                startActivityForResult(intent, 0);
            }
        }else{
            barcode();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Log.d("contents: " , contents+"");
                etBarCode.setText(contents);
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("", "RESULT_CANCELED");
            }
        }
    }
    public void barcode(){
        final String Barcode = etBarCode.getText().toString();
        Response.Listener<String> responsListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    String barcode = etBarCode.getText().toString();
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String id = jsonResponse.getString("id");
                        String paying = jsonResponse.getString("paying");
                        String period = jsonResponse.getString("period");
                        String set = jsonResponse.getString("set");
                        String pay_status = jsonResponse.getString("pay_status");
                        String renter_id = jsonResponse.getString("renter_id");
                        String renter_code = jsonResponse.getString("renter_code");
                        String renter_name = jsonResponse.getString("renter_name");
                        String renter_lastname = jsonResponse.getString("renter_lastname");
                        String renter_image = jsonResponse.getString("renter_image");
                        String stall_price = jsonResponse.getString("stall_price");
                        String rent_type = jsonResponse.getString("rent_type");
                        Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("paying", paying);
                        intent.putExtra("period", period);
                        intent.putExtra("set", set);
                        intent.putExtra("pay_status", pay_status);
                        intent.putExtra("renter_id", renter_id);
                        intent.putExtra("renter_code", renter_code);
                        intent.putExtra("renter_name", renter_name);
                        intent.putExtra("renter_lastname", renter_lastname);
                        intent.putExtra("renter_image", renter_image);
                        intent.putExtra("stall_price", stall_price);
                        intent.putExtra("rent_type", rent_type);

                        startActivity(intent);

                    }else{
//                        String id = jsonResponse.getString("id");
//                        String paying = jsonResponse.getString("paying");
//                        String period = jsonResponse.getString("period");
//                        String set = jsonResponse.getString("set");
//                        String pay_status = jsonResponse.getString("pay_status");
//                        String renter_id = jsonResponse.getString("renter_id");
//                        String renter_code = jsonResponse.getString("renter_code");
//                        String renter_name = jsonResponse.getString("renter_name");
//                        String renter_lastname = jsonResponse.getString("renter_lastname");
//                        String renter_image = jsonResponse.getString("renter_image");
//                        String stall_price = jsonResponse.getString("stall_price");
//                        String rent_type = jsonResponse.getString("rent_type");
//                        Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
//                        intent.putExtra("id", id);
//                        intent.putExtra("paying", paying);
//                        intent.putExtra("period", period);
//                        intent.putExtra("set", set);
//                        intent.putExtra("pay_status", pay_status);
//                        intent.putExtra("renter_id", renter_id);
//                        intent.putExtra("renter_code", renter_code);
//                        intent.putExtra("renter_name", renter_name);
//                        intent.putExtra("renter_lastname", renter_lastname);
//                        intent.putExtra("renter_image", renter_image);
//                        intent.putExtra("stall_price", stall_price);
//                        intent.putExtra("rent_type", rent_type);
//
//                        startActivity(intent);
                        AlertDialog.Builder  Dialog = new AlertDialog.Builder(MainActivity.this);
                                Dialog.setIcon(R.drawable.launcher_icon)
                                .setTitle("ไม่พบข้อมูลที่ค้างชำระ")
                                .setMessage("ผู้เช่าคนที่" + barcode+" ไม่มีการค้างชำระเงิน")
                                .show();
                        Toast.makeText(MainActivity.this,"Result :"+success,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        PaymentResponse paymentResponse = new PaymentResponse(Barcode,responsListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(paymentResponse);
    }
}
