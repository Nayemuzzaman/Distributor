package com.waltonbd.distributor.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waltonbd.distributor.R;
import com.waltonbd.distributor.utils.AppsSettings;

/**
 * Created by nayemuzzaman on 23/09/18.
 */

public class UserProfileFragment extends Fragment {
    public Context mContext;
    public Activity mActivity;
    public TextView txtName, txtAddressName, txtcontact;
    public TextView txtNameTitle, txtAddressTitle, txtcontactCodeTitle;
    public TextView myMarquee;

    //  public TextView salesOfferTitle;
    //   public LinearLayout lisalesOfferPart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_profile, container, false);
        mContext = getContext();
        mActivity=getActivity();
        Intent intent = mActivity.getIntent();
        if (null != intent) {
            String stringData= intent.getStringExtra("name");
            Toast.makeText(mContext,"name :"+stringData,Toast.LENGTH_SHORT).show();
        }
       /// myMarquee = view.findViewById(R.id.myMarquee);

       // RetailerUtils.goToLoginPage(mContext);
        txtNameTitle = view.findViewById(R.id.txtNameTitle);
        txtAddressTitle = view.findViewById(R.id.txtAddressTitle);
        txtcontactCodeTitle = view.findViewById(R.id.txtcontactCodeTitle);



        txtName = view.findViewById(R.id.txtName);
        txtName.setText("" + AppsSettings.getAppsSettings(mContext).getName());
        txtAddressName = view.findViewById(R.id.txtAddressName);
        txtAddressName.setText("" + AppsSettings.getAppsSettings(mContext).getAddress());
        txtcontact = view.findViewById(R.id.txtcontact);
        txtcontact.setText("" + AppsSettings.getAppsSettings(mContext).getContact());

        return view;
    }

}
