package fr.clerc.tp3.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.clerc.tp3.R
import fr.clerc.tp3.kotlin.model.EmployeeData
import kotlinx.android.synthetic.main.item_employee.view.*

class RecyclerViewAdapter(
    private val itemList: ArrayList<EmployeeData>,
    val listener: (EmployeeData) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addEmployee(list: List<EmployeeData>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = itemList[position]

        holder.name.text = employee.employeeName
        holder.age.text = employee.employeeAge.toString()
        holder.salary.text = employee.employeeSalary.toString()

        holder.itemView.setOnClickListener {
            listener(employee)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.item_name
        val age: TextView = view.item_age
        val salary: TextView = view.item_salary
    }
}