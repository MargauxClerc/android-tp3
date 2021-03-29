package fr.clerc.tp3.kotlin.model

import com.google.gson.annotations.SerializedName

data class EmployeeData (
    val id: Int,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_salary")
    val employeeSalary: Int,
    @SerializedName("employee_age")
    val employeeAge: Int
)