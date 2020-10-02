package com.app.myapplication

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_imagen.*

class ImagenActivity : AppCompatActivity() {
    lateinit var filepath: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagen)
        btnElejirImagen.setOnClickListener {
            startFileChoose()
        }
        btnguardar.setOnClickListener {
            uploadFile()
        }

    }
    fun uploadFile(){
        if(filepath!=null){
            var pd = ProgressDialog(this)
            pd.setTitle("Cargando imagen")
            pd.show()
            var imagen: StorageReference= FirebaseStorage.getInstance().reference.child("images/userProfile.jpg")
            imagen.putFile(filepath)
                .addOnSuccessListener {p0->
                    pd.dismiss()
                    Toast.makeText(applicationContext,"Imagen cargada", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {p0->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener {p0->
                    var progress:Double= (100.0 * p0.bytesTransferred)/ p0.totalByteCount
                    pd.setMessage("Cargado ${progress.toInt()}%")
                }
        }
    }
    fun startFileChoose(){
        var i = Intent()
        i.setType("image/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"elegir imagen"),111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==111 && resultCode==Activity.RESULT_OK && data!=null){
            filepath = data.data!!
            var bitmap:Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageViewUserProfile.setImageBitmap(bitmap)
        }

    }
}