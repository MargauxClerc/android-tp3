package fr.clerc.tp3;

import fr.clerc.tp3.model.Employee;
import fr.clerc.tp3.model.Employees;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EmployeesService {

    @GET("/api/v1/employees")
    Call<Employees> getEmployeeList();

    @GET("/api/v1/employee/{id}")
    Call<Employee> getEmployee(@Path("id") int id);
}

