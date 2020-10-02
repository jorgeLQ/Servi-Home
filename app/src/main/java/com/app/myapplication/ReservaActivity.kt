package com.app.myapplication


import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.servi_home.ELEJIR_EMPLEADO


class ReservaActivity : AppCompatActivity() {
    private  var fecha: String?=null
    private  var hora: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        var btnFecha:Button= findViewById(R.id.btnFecha)
        var btnHora:Button= findViewById(R.id.btnHora)
        var btnCon: Button = findViewById(R.id.btnSeguir)
        //var editTextHora = EditText(findViewById(R.id.editTextHora))
        //var editTextFecha = EditText(findViewById(R.id.editTextfecha))

        btnFecha.setOnClickListener {
            val d = java.util.Calendar.getInstance()
            var anio: Int = d.get(java.util.Calendar.YEAR)
            var mes: Int = d.get(java.util.Calendar.MONTH)
            var dia: Int = d.get(java.util.Calendar.DAY_OF_MONTH)
            var textDia: EditText = findViewById(R.id.editTextfecha)

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    fecha= "" + dayOfMonth + "/" + monthOfYear + "/" + year
                    textDia.setText(fecha)
                },
                anio,
                mes,
                dia
            )
            dpd.show()
        }
        btnHora.setOnClickListener {
            val h = java.util.Calendar.getInstance()
            var hora: Int = h.get(java.util.Calendar.HOUR_OF_DAY)
            var min: Int = h.get(java.util.Calendar.MINUTE)
            var textHora: EditText = findViewById(R.id.editTextHora)
            val tmd =
                TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    var hora = "" + hour + ":" + minute
                    textHora.setText(hora)
                }, hora, min, false)
            tmd.show()
        }
        btnCon.setOnClickListener {
            var empl = Intent(this, MainActivity::class.java)
            startActivity(empl)
            Toast.makeText(this, ELEJIR_EMPLEADO, Toast.LENGTH_LONG).show();

        }

    }
    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.logo)
        builder.setTitle("Servicio \n"+"Lavanderias Norte S.A. lavado en seco")
        builder.setMessage("Escoja a un empleado para realizar la limpieza")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}