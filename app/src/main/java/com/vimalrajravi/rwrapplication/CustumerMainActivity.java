package com.vimalrajravi.rwrapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustumerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.rec_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit setretrofit= CustumerRetrofit.retrofitClientInstance();
        CustumerApi custumerApi = setretrofit.create(CustumerApi.class);
        custumerApi.getPost().enqueue(new Callback<List<CustumerDetails>>() {
            @Override
            public void onResponse(Call<List<CustumerDetails>> call, Response<List<CustumerDetails>> response) {
                if (response.isSuccessful()) {
                    List<CustumerDetails> listofpost = response.body();
                    Toast.makeText(CustumerMainActivity.this, "sucessful", Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new CustomRecyclerAdopter(CustumerMainActivity.this,listofpost));
                }
            }
            @Override
            public void onFailure(Call<List<CustumerDetails>> call, Throwable t) {
                Toast.makeText(CustumerMainActivity.this, "failure", Toast.LENGTH_SHORT).show();
                Log.d("recycler", t.getMessage());
            }
        });
    }
    private class CustomRecyclerAdopter extends RecyclerView.Adapter<CustumerViewHolder> {
        private CustumerMainActivity custumerMainActivity;
        private List<CustumerDetails> custumerList;

        public CustomRecyclerAdopter(CustumerMainActivity custumerMainActivity, List<CustumerDetails> postList) {
            this.custumerMainActivity = custumerMainActivity;
            this.custumerList = postList;
        }

        @NonNull
        @Override
        public CustumerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holdertype,parent,false);
            return new CustumerViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull CustumerViewHolder holder, int position) {
            CustumerDetails post = custumerList.get(position);
            holder.despview.setText(post.body);
            holder.listview.setText(post.title);
        }

        @Override
        public int getItemCount() {
            return custumerList.size();
        }
    }
}

