package com.app.myapplication.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.myapplication.R
import com.app.myapplication.models.Empleado


class EmpleadoAdapter (private val context: Activity, private val empleados: ArrayList<Empleado>)   :
    BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var  rowView = View.inflate(context,
            R.layout.empleado_view,null)

        val textViewNombre = rowView.findViewById<TextView>(R.id.texnombre)
        val textViewCalificacion = rowView.findViewById<TextView>(R.id.textcalif)
        val textViewComentario = rowView.findViewById<TextView>(R.id.textCom)
        //val posicion = position + 1
        textViewNombre.text = "${empleados[position].nombre}"
        textViewCalificacion.text = "${empleados[position].calificacion.toString()}"
        textViewComentario.text = "${empleados[position].comentario}"
        return rowView
    }

    override fun getItem(position: Int): Any? {
        return empleados.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return empleados.size
    }
}
