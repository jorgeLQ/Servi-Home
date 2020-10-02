package com.app.myapplication.ui.dashboard

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.myapplication.ImagenActivity
import com.app.myapplication.R
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class DashboardFragment : Fragment() {
    lateinit var filePath: Uri
    lateinit var localFile:File
    var bitmap:Bitmap?= null
    private lateinit var mStorageREference:StorageReference
    private lateinit var imageRef:StorageReference
    private lateinit var storage:FirebaseStorage
    private lateinit var dashboardViewModel: DashboardViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        var btonGuardar:Button = root.findViewById(R.id.btnGuardar)
        var imagen: ImageView= root.findViewById(R.id.imageViewUser)
        var btonUpdate:Button= root.findViewById(R.id.btnUpdate)

       /* storage = FirebaseStorage.getInstance()

        imageRef = storage.getReference().child("images").child("userProfile.jpg")
        imageRef.getBytes(1024*1024)
            .addOnSuccessListener {
                bitmap= BitmapFactory.decodeByteArray(bytes,0, bytes.length)
                imagen.setImageBitmap(bitmap)
            }*/
        storage = FirebaseStorage.getInstance()

        imageRef = storage.getReference()
        imageRef.child("userProfile.jpg")
        /*imageRef.child("userProfile.jpg").downloadUrl.addOnSuccessListener {

            }
            .addOnFailureListener {

            }*/
        Glide.with(this)
            .load("https://www.abc.es/media/peliculas/000/019/042/cars-2.jpg")
            .into(imagen)

        /*mStorageREference = FirebaseStorage.getInstance().getReference()
            .child("userProfile.jpg")
        Glide.with(this)
            .load(imageRef)
            .into(imagen)*/



        //imageref = Firebase.storage.reference.child("image/user.jpg")
                btonGuardar.setOnClickListener {
            Toast.makeText(activity, "Datos Guardados", Toast.LENGTH_SHORT).show();

        }
        btonUpdate.setOnClickListener {
            var intent = Intent(activity,ImagenActivity::class.java)
            startActivity(intent)
        }


        return root
    }



}

