package com.example.yothin_error.barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PaymentActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ImageView ivRenterImage;
    TextView tvId;
    TextView tvCode;
    CheckBox cbIdPay_txt;
    TextView tvIdPayiod_txt;
    TextView tvPayStall_txt;
    TextView tvMoney;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ivRenterImage = (ImageView) findViewById(R.id.ivImageRenter);
        tvId = (TextView) findViewById(R.id.tvId);
        tvCode = (TextView) findViewById(R.id.tvCode);
        cbIdPay_txt = (CheckBox) findViewById(R.id.cbIdPay_txt);
        tvIdPayiod_txt = (TextView) findViewById(R.id.tvPayiod_txt);
        tvPayStall_txt = (TextView) findViewById(R.id.tvPayStall_txt);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String paying = bundle.getString("paying");
        String period = bundle.getString("period");
        String set = bundle.getString("set");
        String pay_status = bundle.getString("pay_status");
        String renter_id = bundle.getString("renter_id");
        String renter_code = bundle.getString("renter_code");
        String renter_name = bundle.getString("renter_name");
        String renter_lastname = bundle.getString("renter_lastname");
        String renter_image = bundle.getString("renter_image");
        final String stall_price = bundle.getString("stall_price");
        String rent_type = bundle.getString("rent_type");

        Toast.makeText(this,"Result : "+id+"\n"+paying+"\n"+period+"\n"+set+"\n"+pay_status+"\n"+renter_id+"\n"+renter_code+"\n"
                +renter_name+"\n"+renter_lastname+"\n"+renter_image+"\n"+stall_price+"\n"+rent_type,Toast.LENGTH_LONG).show();

        Glide.with(this).load("http://172.20.10.9/SC/admin/dist/img/"+renter_image)
                .placeholder(R.drawable.launcher_icon)
                .error(R.drawable.launcher_icon)
                .into(ivRenterImage);
        tvId.setText("รหัสผู้เช่า :"+renter_id+"  รหัสประชาชน :"+renter_code);
        tvCode.setText("ชื่อ: "+renter_name+" "+renter_lastname);
        cbIdPay_txt.setText(id);
        tvIdPayiod_txt.setText(period);
        tvPayStall_txt.setText(stall_price);

        cbIdPay_txt.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String stall_price = tvPayStall_txt.getText().toString();
        if(cbIdPay_txt.isChecked()){
            tvMoney.setText(stall_price);
        }else {
            stall_price = "0";
            tvMoney.setText(stall_price);
        }
    }
}
