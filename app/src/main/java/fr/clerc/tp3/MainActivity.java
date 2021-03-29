package fr.clerc.tp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import fr.clerc.tp3.model.Employee;
import fr.clerc.tp3.model.EmployeeData;
import fr.clerc.tp3.model.Employees;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;

    public static final String EMPLOYEE_ID = "employee_id";

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
            public void onItemClick(View view, EmployeeData employee) {
                Intent intent = new Intent(getApplicationContext(), EmployeeDetailsActivity.class);
                intent.putExtra(EMPLOYEE_ID, employee.getId());
                startActivity(intent);
            }
        });

        //Set the adapter
        recyclerView.setAdapter(recyclerAdapter);

        fetchEmployeeData();
    }

    private void fetchEmployeeData() {
        EmployeesService employeesService = RetrofitClient.getInstance().create(EmployeesService.class);
        employeesService.getEmployeeList().enqueue(new Callback<Employees>() {

            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                if(response.isSuccessful() && response.body() != null) {
                    recyclerAdapter.addEmployeeList(response.body().getData());
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.app_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                //Manage errors
                Log.d("MainActivity",t.getMessage());
            }
        });
    }
}
