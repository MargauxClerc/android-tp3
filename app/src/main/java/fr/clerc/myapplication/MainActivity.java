package fr.clerc.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import fr.clerc.myapplication.model.Feature;
import fr.clerc.myapplication.model.FeatureCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;

    public static final String FEATURE_NAME = "feature_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //Initialize layoutManager with a LinearLayoutManager, by default vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Add list divider between each line
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Create adapter with the OnItemClickListener implementation
        recyclerAdapter = new RecyclerViewAdapter(new ArrayList(), new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Feature feature) {
                Intent intent = new Intent(getApplicationContext(), AgencyDetailsActivity.class);
                intent.putExtra(FEATURE_NAME, feature.getProperties().getName());
                startActivity(intent);
            }
        });

        //Set the adapter
        recyclerView.setAdapter(recyclerAdapter);

        fetchMetromobiliteData();
    }

    private void fetchMetromobiliteData() {
        MetromobiliteService metroService = RetrofitClient.getInstance().create(MetromobiliteService.class);
        metroService.getAgencyList("agenceM").enqueue(new Callback<FeatureCollection>() {

            @Override
            public void onResponse(Call<FeatureCollection> call, Response<FeatureCollection> response) {
                if(response.isSuccessful() && response.body() != null) {
                    //Manage data
                    FeatureCollection collection = response.body();
                    recyclerAdapter.addFeatureList(collection.getFeatureList());
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.app_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FeatureCollection> call, Throwable t) {
                //Manage errors
            }
        });
    }
}
