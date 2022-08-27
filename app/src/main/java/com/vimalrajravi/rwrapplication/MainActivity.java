package com.vimalrajravi.rwrapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.rec_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Retrofit setretrofit=GetRetrofit.retrofitClientInstance();
        Api api = setretrofit.create(Api.class);
        api.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> listofpost = response.body();
                    Toast.makeText(MainActivity.this, "sucessful", Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new CustomRecyclerAdopter(MainActivity.this,listofpost));


                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
                Log.d("recycler", t.getMessage());

            }
        });

    }

    private class CustomRecyclerAdopter extends RecyclerView.Adapter<CusstomVIewHolder> {
        private List<Post> postList;

        public CustomRecyclerAdopter(MainActivity mainActivity, List<Post> postList) {
            this.postList = postList;
        }

        @NonNull
        @Override
        public CusstomVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holdertype,parent,false);
            return new CusstomVIewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull CusstomVIewHolder holder, int position) {
            Post post = postList.get(position);
            holder.despview.setText(post.body);
            holder.listview.setText(post.title);
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }
    }
}

class CusstomVIewHolder extends RecyclerView.ViewHolder{
    TextView despview;
    TextView listview;
    public CusstomVIewHolder(@NonNull View itemView) {
        super(itemView);
        despview=itemView.findViewById(R.id.des_view);
        listview=itemView.findViewById(R.id.lis_view);

    }

}
