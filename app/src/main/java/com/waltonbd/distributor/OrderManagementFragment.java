package com.waltonbd.distributor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.waltonbd.distributor.activity.DistributtorNetworkActivity;

/**
 * Created by nayemuzzaman on 22/09/18.
 */


public class OrderManagementFragment extends Fragment {
    Button btnCreateRP,btnExitingRP;
    Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.fragment_order_management,container,false);
        mActivity=getActivity();
        btnCreateRP=view.findViewById(R.id.btnNewRp);
        btnExitingRP=view.findViewById(R.id.btnExistingRP);
        btnCreateRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity, DistributtorNetworkActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
