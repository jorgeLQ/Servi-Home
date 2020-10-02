package com.app.myapplication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import com.app.servi_home.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private  val GOOGLE_SING_IN = 100
    private lateinit var auth: FirebaseAuth
    private var mIsShowPass = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance();
        btnRegistrase.setOnClickListener {
            var resgistrarse = Intent(this, RegistraseActivity::class.java)
            startActivity(resgistrarse)
        }
        textView3.setOnClickListener {
            var resContra = Intent(this, RestablecerContraseniaActivity::class.java)
            startActivity(resContra)
        }
        showContrasenia.setOnClickListener {
            mIsShowPass=!mIsShowPass
            verContrasenia(mIsShowPass)
        }
        btnInicio.setOnClickListener {
            inicioSesion();
        }
        btnGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }
    }
    fun inicioSesion(){
        CORREO = editTextTextCorreo.getText().toString();
        CONTRASENIA = editTextTextPassword.text.toString();
        if (!CORREO.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(CORREO).matches() &&
            !CONTRASENIA.isEmpty() && CONTRASENIA.length >= 8) {
            auth.signInWithEmailAndPassword(CORREO, CONTRASENIA).addOnCompleteListener{
                if(it.isSuccessful){
                    limpiarCampos()
                    showHomeUsuario()
                    Toast.makeText(this, MENSAJE_BIENVEINDA, Toast.LENGTH_SHORT).show()
                }else{
                    showAlert()
                }
            }
        }
        if (CORREO.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(CORREO).matches()) {
            editTextTextCorreo.setError(MENSAJE_ERROR_CORREO)
        }
        if (CONTRASENIA.isEmpty() || CONTRASENIA.length < 8) {
            editTextTextPassword.setError(MENSAJE_ERROR_CONTRASENIA)
        }
    }
    fun limpiarCampos(){
        editTextTextCorreo.setText("");
        editTextTextPassword.setText("");
    }
    fun showHomeUsuario(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.putExtra("USER_EMAIL",auth.currentUser?.email)
        startActivity(homeIntent)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==GOOGLE_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account!= null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    auth.signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHomeUsuario()
                            Toast.makeText(this, MENSAJE_BIENVEINDA, Toast.LENGTH_SHORT).show()
                        }else{
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException){
                showAlert()
            }

        }
    }
    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_error)
        builder.setTitle("Error")
        builder.setMessage("El usuario no se encuentra registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun verContrasenia(isShow: Boolean){
        if(isShow){
            editTextTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            showContrasenia.setImageResource(R.drawable.ic_hide_password_24dp)
        }else{
            editTextTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            showContrasenia.setImageResource(R.drawable.ic_show_password_24dp)
        }
        editTextTextPassword.setSelection(editTextTextPassword.text.toString().length)
    }
}