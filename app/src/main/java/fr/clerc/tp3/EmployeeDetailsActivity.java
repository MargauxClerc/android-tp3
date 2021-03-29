package fr.clerc.tp3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fr.clerc.tp3.model.Employee;
import fr.clerc.tp3.model.EmployeeData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static fr.clerc.tp3.MainActivity.EMPLOYEE_ID;

public class EmployeeDetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView age;
    private TextView salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employee_detail);

        name = findViewById(R.id.employee_name);
        age = findViewById(R.id.employee_age);
        salary = findViewById(R.id.employee_salary);

        Toolbar toolbar = findViewById(R.id.employeeDetailToolbar);
        toolbar.setTitle(getString(R.string.employee_label));
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
            int id = bundle.getInt(EMPLOYEE_ID, -1);
            if (id >= 0) fetchAgencyData(id);
        }
    }

    private void fetchAgencyData(int id) {
        EmployeesService employeesService = RetrofitClient.getInstance().create(EmployeesService.class);
        employeesService.getEmployee(id).enqueue(new Callback<Employee>() {

            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(response.isSuccessful() && response.body() != null) {
                    //Manage data
                    displayData(response.body().getData());
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.app_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                //Manage errors
            }
        });
    }

    private void displayData(EmployeeData employee) {
        name.setText(employee.getEmployeeName());
        age.setText(String.valueOf(employee.getEmployeeAge()));
        salary.setText(String.valueOf(employee.getEmployeeSalary()));
    }
}
