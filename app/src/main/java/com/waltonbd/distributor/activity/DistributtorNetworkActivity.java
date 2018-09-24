package com.waltonbd.distributor.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.waltonbd.distributor.R;
import com.waltonbd.distributor.constant.Constant;
import com.waltonbd.distributor.fragment.UserProfileFragment;
import com.waltonbd.distributor.utils.AppsSettings;
import com.waltonbd.distributor.utils.InternetConnectionCheck;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nayemuzzaman on 23/09/18.
 */

public class DistributtorNetworkActivity extends AppCompatActivity {
    Button btnCreate;
    Context mContext;
    EditText etName, etProprietorName, etAddress, etContact, etShopCategory;
    public String mName;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributtor_network);
        mContext = this;
        pDialog=new ProgressDialog(mContext);
        initialize();
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetConnectionCheck.checkConn(mContext)) {
                    if (inputValidation()) {
                        String url = Constant.DISTRIBUTORNETWORK;
                        SalesAPi(url);
                   // Toast.makeText(mContext,"ok ok",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, UserProfileFragment.class);
                        intent.putExtra("name", mName);
                        startActivity(intent);

                        Bundle bundle = new Bundle();
                        bundle.putString("params", mName);

                        Fragment myObj = new Fragment();
                        myObj.setArguments(bundle);

                    }

                }
            }
        });

    }

    /*
     * initialize view
     * */
    private void initialize() {
        etName = findViewById(R.id.etDistributrorName);
        etAddress = findViewById(R.id.etAddress);
        etProprietorName = findViewById(R.id.etProprietorName);
        etContact = findViewById(R.id.etContact);
        etShopCategory = findViewById(R.id.etShopCategory);


    }

    /*input validation check
     *
     * */
    private boolean inputValidation() {


        if (etName.getText().toString().trim().isEmpty()) {

            etName.setError("Name required");
            return false;
        } else if (etProprietorName.getText().toString().trim().isEmpty()) {

            etProprietorName.setError("Proprietor Name required");
            return false;
        } else if (etAddress.getText().toString().trim().isEmpty()) {

            etAddress.setError("Address Name required");
            return false;
        } else if (etContact.getText().toString().trim().isEmpty()) {

            etContact.setError("Contact Name required");
            return false;
        } else if (etShopCategory.getText().toString().trim().isEmpty()) {
            etShopCategory.setError("ShopCategory required");
        }
        return true;
    }

    /*
     * api call .post method call here
     *
     * */
    private void SalesAPi(String url) {

        pDialog.setMessage("Loading! Please wait . . .");
        pDialog.show();
        System.out.println("inside login");
        RequestQueue mRequestQueue = Volley
                .newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("response of login is:" + response);

                        try {
                            JSONObject json = new JSONObject(response);
                            String usrname = json.getString("id");
                            Log.e("id", usrname);


                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Please Enter Valid Input", Toast.LENGTH_SHORT).show();

            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + AppsSettings.getAppsSettings(mContext).getAccessKey());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                mName=etProprietorName.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("name", "salesapi");
                params.put("presentAddress", "thana para");
                params.put("mobilePhone", "010101");
                params.put("shopCategory", etShopCategory.getEditableText().toString());
                params.put("proprietorName", mName);
                return params;

            }
        };
        int socketTimeout = 30000; // 30 seconds - change
        // to what you want
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        mRequestQueue.add(postRequest);
    }

}
