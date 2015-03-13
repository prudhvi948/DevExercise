package com.devexercise.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.devexercise.app.R;
import com.devexercise.app.models.AccountsData;
import com.devexercise.app.services.DataCalculation;

import java.util.List;

/**
* Created by prudhvi on 3/4/2015.
*/
public class AccountsArrayAdapter extends ArrayAdapter {
    private Activity activity;

    public AccountsArrayAdapter(Context context, List<AccountsData> accounts) {
        super(context, 0, accounts);
    }

    public View getView(int position, View view, ViewGroup viewGroup)
    {
        AccountsCell accountsCell = new AccountsCell();

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.cell_accounts, viewGroup, false);

        Typeface fontMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface fontRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface fontLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
        Typeface fontThin = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");

        accountsCell.displayName = (TextView) view.findViewById(R.id.display_name);
        accountsCell.displayName.setTypeface(fontMedium);
        accountsCell.time = (TextView) view.findViewById(R.id.time);
        accountsCell.time.setTypeface(fontLight);
        accountsCell.amount = (TextView) view.findViewById(R.id.amount);
        accountsCell.amount.setTypeface(fontThin);
        accountsCell.engagement = (TextView) view.findViewById(R.id.engagement);
        accountsCell.engagement.setTypeface(fontLight);
        accountsCell.activeUsers = (TextView) view.findViewById(R.id.active_users);
        accountsCell.activeUsers.setTypeface(fontLight);

        AccountsData accountsData = (AccountsData) getItem(position);
        DataCalculation dataCalculation = new DataCalculation();

        accountsCell.displayName.setText(accountsData.getDisplayName());
        accountsCell.time.setText(dataCalculation.getDays(accountsData.getTime())+" "+accountsCell.time.getText());
        accountsCell.amount.setText(dataCalculation.getAmount(accountsData.getAmount()));
        accountsCell.engagement.setText(accountsCell.engagement.getText()+" "+Integer.toString(accountsData.getEngagement())+"/100");
        accountsCell.activeUsers.setText(accountsCell.activeUsers.getText()+" "+Integer.toString(accountsData.getActiveUsers()));

        return view;
    }

    private static class AccountsCell
    {
        TextView displayName;
        TextView time;
        TextView amount;
        TextView engagement;
        TextView activeUsers;
    }
}
