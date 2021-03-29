package fr.clerc.tp3.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.clerc.tp3.R
import fr.clerc.tp3.RetrofitClient
import fr.clerc.tp3.kotlin.model.Employee
import fr.clerc.tp3.kotlin.model.EmployeeData
import kotlinx.android.synthetic.main.activity_employee_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_employee_detail)

        employeeDetailToolbar.title = getString(R.string.employee_label)
        setSupportActionBar(employeeDetailToolbar)

        employeeDetailToolbar.setNavigationIcon(R.drawable.ic_back)
        employeeDetailToolbar.setNavigationOnClickListener {
            finish()
        }

        val id = intent.getIntExtra(MainActivity.EMPLOYEE_ID, 0)
        if (id >= 0) fetchEmployeeData(id)
    }

    private fun fetchEmployeeData(id: Int) {
        val employeesService = RetrofitClient.getInstance().create(EmployeesService::class.java)
        employeesService.getEmployee(id).enqueue(object : Callback<Employee?> {
            override fun onResponse(call: Call<Employee?>, response: Response<Employee?>) {
                if (response.isSuccessful && response.body() != null) { //Manage data
                    val employee = response.body()
                    if(employee != null) {
                        displayData(employee.data)
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.app_error), Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Employee?>, t: Throwable) { //Manage errors
            }
        })
    }

    private fun displayData(data: EmployeeData) {
        employee_name.text = data.employeeName
        employee_age.text = data.employeeAge.toString()
        employee_salary.text = data.employeeSalary.toString()
    }
}