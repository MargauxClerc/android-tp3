package fr.clerc.tp3.kotlin

import fr.clerc.tp3.kotlin.model.Employee
import fr.clerc.tp3.kotlin.model.Employees
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeesService {

    @GET("/api/v1/employees")
    fun getEmployeeList(): Call<Employees?>

    @GET("/api/v1/employee/{id}")
    fun getEmployee(@Path("id") id: Int): Call<Employee?>
}