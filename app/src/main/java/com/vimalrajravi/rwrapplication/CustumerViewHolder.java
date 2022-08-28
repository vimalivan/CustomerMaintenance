package com.vimalrajravi.rwrapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CustumerViewHolder extends RecyclerView.ViewHolder {
    TextView despview;
    TextView listview;

    public CustumerViewHolder(@NonNull View itemView) {
        super(itemView);
        despview = itemView.findViewById(R.id.des_view);
        listview = itemView.findViewById(R.id.lis_view);

    }

}
