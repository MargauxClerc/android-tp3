package fr.clerc.tp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.clerc.tp3.model.EmployeeData;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final ArrayList<EmployeeData> employeeList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, EmployeeData employee);
    }

    public RecyclerViewAdapter(ArrayList<EmployeeData> employeeList, OnItemClickListener listener) {
        this.employeeList = employeeList;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.employeeList.size();
    }

    public void addEmployeeList(List<EmployeeData> employeeList) {
        this.employeeList.addAll(employeeList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final EmployeeData employeeData = employeeList.get(position);

        holder.name.setText(employeeData.getEmployeeName());
        holder.age.setText(String.valueOf(employeeData.getEmployeeAge()));
        holder.salary.setText(String.valueOf(employeeData.getEmployeeSalary()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, employeeData);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        final TextView name;
        final TextView age;
        final TextView salary;

        ViewHolder(View view) {
            super(view);
            itemView = view;
            name = view.findViewById(R.id.item_name);
            age = view.findViewById(R.id.item_age);
            salary = view.findViewById(R.id.item_salary);
        }
    }
}
