package mx.edu.tecmm.moviles.contador

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Modify : AppCompatActivity() {

    lateinit var txtNameSi : EditText
    lateinit var txtDegreeSi : EditText
    lateinit var txtPhoneSi : EditText
    lateinit var btnSave : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modify)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNameSi = findViewById(R.id.txtNameMf)
        txtDegreeSi = findViewById(R.id.txtDegreeMf)
        txtPhoneSi = findViewById(R.id.txtPhoneMf)
        btnSave = findViewById(R.id.btnSave)

        var Name : String
        var Phone : String
        var Degree : String

        val preferencias = getSharedPreferences("Data", Context.MODE_PRIVATE)
        Name = preferencias.getString("Name", "xxx").toString()
        Phone = preferencias.getString("Phone", "xxx").toString()
        Degree = preferencias.getString("Degree", "xxx").toString()

        txtNameSi.setText(Name)
        txtDegreeSi.setText(Degree)
        txtPhoneSi.setText(Phone)

        fun updateNameDegreePhone() {
            val newName = txtNameSi.text.toString()
            val newDegree = txtDegreeSi.text.toString()
            val newPhone = txtPhoneSi.text.toString()

            val preferencias = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("Name", newName)
            editor.putString("Degree", newDegree)
            editor.putString("Phone", newPhone)
            editor.apply()

            // Actualizar los campos de texto después de guardar los cambios
            txtNameSi.setText(newName)
            txtDegreeSi.setText(newDegree)
            txtPhoneSi.setText(newPhone)

            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
        }


        btnSave.setOnClickListener(){
            updateNameDegreePhone()
            finish()
        }


    }
}