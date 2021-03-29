package fr.clerc.tp3.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.clerc.tp3.MainActivity
import fr.clerc.tp3.R
import fr.clerc.tp3.kotlin.model.Employees
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter

    companion object {
        const val EMPLOYEE_ID: String = "employee_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemDecoration = DividerItemDecoration(this, VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        adapter = RecyclerViewAdapter(ArrayList()) { employee ->
            val intent = Intent(this, EmployeeDetailsActivity::class.java)
                .apply {
                    putExtra(EMPLOYEE_ID, employee.id)
                }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        fetchEmployeeList()
    }

    private fun fetchEmployeeList() {
        val employeesService = RetrofitClient.retrofit.create(EmployeesService::class.java)
        employeesService.getEmployeeList().enqueue(object: Callback<Employees?>{
            override fun onFailure(call: Call<Employees?>, t: Throwable?) {
                //Manage errors
                Log.d("MainActivity","${t?.message}")
            }

            override fun onResponse(call: Call<Employees?>, response: Response<Employees?>) {
                if (response.isSuccessful) {
                    response.body()?.let { adapter.addEmployee(it.data) }
                } else {
                    Toast.makeText(applicationContext, getString(R.string.app_error), Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}