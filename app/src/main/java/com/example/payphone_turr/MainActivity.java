package com.example.payphone_turr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    int televisor_price;

    int amount, amountWithTax, amountWithoutTax, tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buyTV (View view){
        televisor_price = 700;
        obtenerTV();
    }

    public void buySecondTV (View view){
        televisor_price = 1100;
        obtenerTV();
    }

    public void buyThirdTV (View view){
        televisor_price = 3000;
        obtenerTV();
    }

    public void buyLastTV (View view){
        televisor_price = 700;
        obtenerTV();
    }

    /*
  { \
  "phoneNumber": "992304169", \
  "countryCode": "593", \
  "clientUserId": "1205127366", \
  "reference": "none", \
  "responseUrl": "http://paystoreCZ.com/confirm.php", \
  "amount": 100, \ var
  "amountWithTax": 90, \ var
  "amountWithoutTax": 0, \ var
  "tax": 10, \ var
  "clientTransactionId": "12345", \
   }’
   'https://pay.payphonetodoesposible.com/api/Sale'
   */

    private void obtenerTV(){
        amount = televisor_price * 100;
        tax = 0;
        int random = new Random().nextInt() * 10000;
        Map <String, String> map = new HashMap<>();
        map.put("phoneNumber","996426832");
        map.put("countryCode","593");
        map.put("clientUserId","1207790294001");
        map.put("reference","Venta de Televisor");
        map.put("responseUrl","http://paystoreCZ.com/confirm.php");
        map.put("amount", String    .valueOf(amount));
        map.put("amountWithTax","0");
        map.put("amountWithoutTax", String.valueOf(amount));
        map.put("tax","0");
        map.put("clientTransactionId", String.valueOf(random));

        String url = "https://pay.payphonetodoesposible.com/api/Sale";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url , null ,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String trans = response.getString("transactionId");
                            Toast.makeText(MainActivity.this, trans, Toast.LENGTH_LONG);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        ) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("Content-Type", "application/json");
                headerMap.put("Accept", "application/json");
                headerMap.put("Authorization", "Bearer qx80fU5xyoNbwTmm038SKXR7OxvM6k3leAxIsabT9-Ev0eJQhopNA08lCZp4ueWWlkguLVPOp8ShgLuM4eUvQotEIhaSkzWyuTWgfs6pzyxTJ3_5P3yExwnu-9Pxo153OtQpj78KGoeaZONz_lIFSXbtSDwowcqu_El6jDXaRzNqIWzL61O2xsoES17AFMh8D2ykrUxqfgtBf7_HaqaVdiUL0s46pUq4QNMIE0fCUE4fJ1O0e_6SWfNHdiAAsJP2za1J4rEaWCqAInlTOz2ZAx3rb7_-TsehUsBysmj3ajE7zDgDfa9ekFAVDRy_dHZIQDKPIJWj78AzcpRDsWlVWQs-nbs");
                return headerMap;
            }
        };
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
    }
}