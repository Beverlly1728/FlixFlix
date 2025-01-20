package id.example.shashank.flixflix.Activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import id.example.shashank.flixflix.Adapter.FilmListAdapter;
import id.example.shashank.flixflix.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovies, adapterUpcoming;
    private RecyclerView recyclerViewMovies, recyclerViewUpComing;
    private RequestQueue mRequestQueue;
    private ProgressBar loading1, loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        sendRquest1();
        sendRquest2();
    }

    private void sendRquest1() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            id.example.shashank.flixflix.Domain.ListFilm items = gson.fromJson(response, id.example.shashank.flixflix.Domain.ListFilm.class);
            adapterNewMovies = new FilmListAdapter(items);
            recyclerViewMovies.setAdapter(adapterNewMovies);
        }, error -> loading1.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }
    private void sendRquest2() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        StringRequest mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);

            id.example.shashank.flixflix.Domain.ListFilm items = gson.fromJson(response, id.example.shashank.flixflix.Domain.ListFilm.class);
            adapterUpcoming = new FilmListAdapter(items);
            recyclerViewUpComing.setAdapter(adapterUpcoming);
        }, error -> loading2.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest2);
    }

    private void initView() {
        recyclerViewMovies = findViewById(R.id.view1);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewUpComing = findViewById(R.id.view2);
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
    }
}