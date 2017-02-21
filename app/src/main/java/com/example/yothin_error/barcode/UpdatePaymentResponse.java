package com.example.yothin_error.barcode;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yothin_Error on 21/2/2560.
 */

public class UpdatePaymentResponse extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://10.0.3.2/sc/admin/android/test/UpdatePayment.php";
    private Map<String, String> params;

    public UpdatePaymentResponse(String renter, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("renter", renter+"");


    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}
