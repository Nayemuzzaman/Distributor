package com.waltonbd.distributor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.waltonbd.distributor.utils.AppConstants;
import com.waltonbd.distributor.utils.AppsSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText etName, etPass;
    Button btnLogin;
    private String username = "username";
    private String password = "password";
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.et_Name);
        etPass = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_server_login);
        mContext = this;
    }

    public void myLogin(View view) {
        Map<String, String> params = new HashMap();
      /*  params.put(username, etName.getText().toString().trim());
        params.put(password, etPass.getText().toString().trim());*/
        params.put(username, "salesapi");
        params.put(password, "123456");

        JSONObject parameters = new JSONObject(params);
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, AppConstants.BASE_URL, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", response.toString());
                try {
                    String name = response.getString("username");
                    Log.e("user name", name);
                    JSONArray jsonArray = response.getJSONArray("roles");
                    for (int i = 0; i < 1; i++) {
                        String roleOne = String.valueOf(jsonArray.get(0));
                        String roleTwo = String.valueOf(jsonArray.get(1));
                    }
                    String accessToken = response.getString("access_token");
                    AppsSettings.getAppsSettings(mContext).setAccessKey(accessToken);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

    }
}
