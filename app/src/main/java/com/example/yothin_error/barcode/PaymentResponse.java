package com.example.yothin_error.barcode;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yothin_Error on 19/2/2560.
 */

public class PaymentResponse extends StringRequest {
    private static final String PAYMENT_REQUEST_URL = "http://10.0.3.2/sc/admin/android/test/Payment.php";
    private Map<String,String> params;


    public PaymentResponse(String Barcode, Response.Listener<String> listener) {
        super(Method.POST, PAYMENT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("barcode",Barcode);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
