package fr.clerc.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fr.clerc.myapplication.model.Feature;
import fr.clerc.myapplication.model.FeatureCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static fr.clerc.myapplication.MainActivity.FEATURE_NAME;

public class AgencyDetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView address;
    private TextView type;
    private TextView street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agency_detail);

        name = findViewById(R.id.feature_name);
        street = findViewById(R.id.feature_street);
        address = findViewById(R.id.feature_address);
        type = findViewById(R.id.feature_type);

        Toolbar toolbar = findViewById(R.id.agencyDetailToolbar);
        toolbar.setTitle(getString(R.string.agence_label));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String name = bundle.getString(FEATURE_NAME);
            fetchAgencyData(name);
        }
    }

    private void fetchAgencyData(String name) {
        MetromobiliteService metroService = RetrofitClient.getInstance().create(MetromobiliteService.class);
        metroService.getAgency("agenceM", name).enqueue(new Callback<FeatureCollection>() {

            @Override
            public void onResponse(Call<FeatureCollection> call, Response<FeatureCollection> response) {
                if(response.isSuccessful() && response.body() != null) {
                    //Manage data
                    FeatureCollection collection = response.body();
                    displayData(collection.getFeatureList().get(0));
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

    private void displayData(Feature feature) {
        name.setText(feature.getProperties().getName());
        type.setText(feature.getProperties().getType());
        street.setText(feature.getProperties().getStreet());
        address.setText(feature.getProperties().getPostalCode() + " - " + feature.getProperties().getCity());
    }
}
