package fr.clerc.myapplication.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.clerc.myapplication.MetromobiliteService
import fr.clerc.myapplication.R
import fr.clerc.myapplication.RetrofitClient
import fr.clerc.myapplication.model.Feature
import fr.clerc.myapplication.model.FeatureCollection
import kotlinx.android.synthetic.main.activity_agency_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgencyDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agency_detail)

        agencyDetailToolbar.title = getString(R.string.agence_label)
        setSupportActionBar(agencyDetailToolbar)

        agencyDetailToolbar.setNavigationIcon(R.drawable.ic_back)
        agencyDetailToolbar.setNavigationOnClickListener {
            finish()
        }

        val name = intent.getStringExtra(MainActivity.FEATURE_NAME)
        name?.let {
            fetchAgencyData(it)
        }
    }

    private fun fetchAgencyData(name: String) {
        val metroService = RetrofitClient.getInstance().create(MetromobiliteService::class.java)
        metroService.getAgency("agenceM", name).enqueue(object : Callback<FeatureCollection?> {
            override fun onResponse(call: Call<FeatureCollection?>, response: Response<FeatureCollection?>) {
                if (response.isSuccessful && response.body() != null) { //Manage data
                    val collection = response.body()
                    if(collection != null) {
                        displayData(collection.featureList[0])
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.app_error), Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<FeatureCollection?>, t: Throwable) { //Manage errors
            }
        })
    }

    private fun displayData(feature: Feature) {
        feature_name.text = feature.properties.name
        feature_type.text = feature.properties.type
        feature_street.text = feature.properties.street
        feature_address.text = feature.properties.postalCode.toString() + " - " + feature.properties.city

    }
}