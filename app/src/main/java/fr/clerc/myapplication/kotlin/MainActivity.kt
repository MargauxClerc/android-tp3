package fr.clerc.myapplication.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.clerc.myapplication.R
import fr.clerc.myapplication.kotlin.model.FeatureCollection
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter

    companion object {
        const val FEATURE_NAME: String = "feature_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemDecoration = DividerItemDecoration(this, VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        adapter = RecyclerViewAdapter(ArrayList()) { feature ->
            val intent = Intent(this, AgencyDetailsActivity::class.java)
            intent.putExtra(FEATURE_NAME, feature.properties.name)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        fetchMetromobiliteData()
    }

    private fun fetchMetromobiliteData() {
        val metroService = RetrofitClient.getInstance().create(MetromobiliteService::class.java)
        metroService.getAgencyList("agenceM").enqueue(object: Callback<FeatureCollection?>{
            override fun onFailure(call: Call<FeatureCollection?>?, t: Throwable?) {
                //Manage errors
            }

            override fun onResponse(call: Call<FeatureCollection?>?, response: Response<FeatureCollection?>?) {
                if (response != null && response.isSuccessful) {
                    val collection = response.body()
                    if(collection != null) {
                        adapter.addFeatureList(collection.featureList)
                    }
                } else {
                    Toast.makeText(applicationContext, getString(R.string.app_error), Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}