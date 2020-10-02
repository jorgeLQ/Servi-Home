package com.app.myapplication.ui.notifications

import android.app.AlertDialog
import android.content.ClipData
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.ReservaActivity
import com.app.myapplication.adapters.EmpleadoAdapter
import com.app.myapplication.models.Empleado
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_reserva.*


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var employAdapter: EmpleadoAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private  var nombre: String?=null
    private  var nombreM: String?=null
    lateinit var mDatabase: FirebaseFirestore
    var arrayLisEmp = arrayListOf<Empleado>()
    //private val adapterEmpleado = EmpleadoAdapter(this, empleados, R.layout.fragment_notifications)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_notifications, container, false)




        var textViewN: TextView = root.findViewById(R.id.textViewNombre)
        var textView2: TextView = root.findViewById(R.id.textViewCalificacion)
        var textView3: TextView = root.findViewById(R.id.textViewComentario)

        var textView4: TextView = root.findViewById(R.id.textView18)
        var textView5: TextView = root.findViewById(R.id.textView19)
        var textView6: TextView = root.findViewById(R.id.textView20)
        var imgView: ImageView = root.findViewById(R.id.imageView8)
        var imgView2: ImageView = root.findViewById(R.id.imageView10)
        var btnreservar: Button = root.findViewById(R.id.btnReservar)

        imgView.setOnClickListener {
            showAlert()
        }
        imgView2.setOnClickListener {
            showAlert2()
        }
        btnreservar.setOnClickListener {
            showAlert3()
        }

        //recyclearViewEmpleados
        /*val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("lavanderia").get().addOnCompleteListener {
            if (it.isSuccessful)
                lista.clear()
            for (documentos in it.result!!){
                val nombre= documentos.getString("nombre")
                val calificacion:Long? = documentos.get("calificacion") as Long?
                val comentario= documentos.getString("comentario")
                if (nombre !=null && calificacion!=null && comentario!= null){
                    lista.add(nombre,calificacion,comentario)
                }
            }
        }*/

        /*val db = FirebaseFirestore.getInstance()
        db.collection("lavanderia").document("WWwgjFGLiRDXD5c7EMh3")
            .get()
            .addOnSuccessListener { documents ->
            for (documentos in documents){
                val nombre= documentos.getString("nombre").toString()
                val calificacion = documentos.get("calificacion").toString()
                val comentario= documentos.getString("comentario").toString()
                val listaEmp =  Empleado(nombre, calificacion,comentario)
                    arrayLisEmp.add(listaEmp)

            }
                var listaEmadap = EmpleadoAdapter(root.context as Activity, arrayLisEmp)
                val listView = root!!.findViewById(R.id.listaEmpleados) as ListView
                listView.adapter = listaEmadap
                Log.d("TAG",arrayLisEmp.toString())

        }*/
        val db = FirebaseFirestore.getInstance()
        db.collection("lavanderia").document("WWwgjFGLiRDXD5c7EMh3")
            .get()
            .addOnSuccessListener { documento ->
                if (documento.exists()) {
                    //val document = task.result
                    nombre = documento.getString("nombre")
                    var calificacion: String? = documento.getString("calificacion")
                    var comentario: String? = documento.getString("comentario")
                    Log.d("datos empleado","$nombre $calificacion $comentario")
                    textViewN.setText(nombre)
                    textView2.setText(calificacion)
                    textView3.setText(comentario)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        db.collection("lavanderia").document("Uvv3gGfvIAJtQAXoilRX")
            .get()
            .addOnSuccessListener { documento ->
                if (documento.exists()) {
                    //val document = task.result
                    nombreM= documento.getString("nombre")
                    var calificacionM: String? = documento.getString("calificacion")
                    var comentarioM: String? = documento.getString("comentario")
                    Log.d("datos empleado","$nombreM $calificacionM $comentarioM")
                    textView4.setText(nombreM)
                    textView5.setText(calificacionM)
                    textView6.setText(comentarioM)
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }


        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
    fun showAlert(){
        val builder = AlertDialog.Builder(activity)
        builder.setIcon(R.drawable.ic_washing_machine)
        builder.setTitle("Servicio \n"+"Lavanderias Norte S.A. lavado en seco")
        builder.setMessage("Usted a solicitado que "+ nombre + " realice el lavado de ropa el dia 03/20/2020" + " a las 12:35 " +
                "\n Desea confirmar la reserva")
        builder.setPositiveButton("Aceptar", null)
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showAlert2(){
        val builder = AlertDialog.Builder(activity)
        builder.setIcon(R.drawable.ic_washing_machine)
        builder.setTitle("Servicio \n"+"Lavanderias Norte S.A. lavado en seco")
        builder.setMessage("Usted a solicitado que "+ nombreM + " realice el lavado de ropa el dia 03/20/2020" + " a las 12:35 " +
                "\n Desea confirmar la reserva")
        builder.setPositiveButton("Aceptar", null)
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun showAlert3(){
        val builder = AlertDialog.Builder(activity)
        builder.setIcon(R.drawable.logo)
        builder.setTitle("SERVI-HOME")
        builder.setMessage("Gracias por comprar nuestros servicios...!!!")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}

