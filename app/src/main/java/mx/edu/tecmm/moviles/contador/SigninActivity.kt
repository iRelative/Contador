package mx.edu.tecmm.moviles.contador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SigninActivity : AppCompatActivity() {

    lateinit var NameT : TextView
    lateinit var DegreeT : TextView
    lateinit var PhoneT : TextView

    lateinit var btnChange : Button
    lateinit var btnSignOut : Button

    override fun onResume() {
        super.onResume()

        val preferencias = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val Name = preferencias.getString("Name", "xxx").toString()
        val Phone = preferencias.getString("Phone", "xxx").toString()
        val Degree = preferencias.getString("Degree", "xxx").toString()

        NameT.setText(Name)
        DegreeT.setText(Degree)
        PhoneT.setText(Phone)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        NameT = findViewById(R.id.Name)
        DegreeT = findViewById(R.id.Degree)
        PhoneT = findViewById(R.id.Phone)

        var Name : String
        var Phone : String
        var Degree : String

        val preferencias = getSharedPreferences("Data", Context.MODE_PRIVATE)
        Name = preferencias.getString("Name", "xxx").toString()
        Phone = preferencias.getString("Phone", "xxx").toString()
        Degree = preferencias.getString("Degree", "xxx").toString()

        NameT.setText(Name)
        DegreeT.setText(Degree)
        PhoneT.setText(Phone)

        // Encuentra el botón por su ID
        val btnSignOut = findViewById<Button>(R.id.btnSave)

        // Asigna un OnClickListener al botón
        btnSignOut.setOnClickListener {
            // Cierra la actividad actual

            val preferencia = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val editor = preferencia.edit()
            editor.putBoolean("Session", false)
            editor.commit()
            finish()
        }



        btnChange = findViewById<Button>(R.id.btnChange)

        btnChange.setOnClickListener {
            val intent = Intent(this, Modify::class.java)
            startActivity(intent)
        }



    }
}