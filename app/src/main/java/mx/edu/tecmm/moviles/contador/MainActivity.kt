package mx.edu.tecmm.moviles.contador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.se.omapi.Session
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var txtUsernameL : TextInputLayout
    lateinit var txtPasswordL : TextInputLayout

    lateinit var btnLogin : Button
    lateinit var Register : TextView

    lateinit var edUsernameL : EditText
    lateinit var edPasswordL : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        txtUsernameL = findViewById(R.id.txtUsernameL)
        txtPasswordL = findViewById(R.id.txtPasswordL)

        edUsernameL = findViewById(R.id.edUsernameL)
        edPasswordL = findViewById(R.id.edPasswordL)

        btnLogin = findViewById(R.id.btnLogin)
        Register = findViewById(R.id.Register)

        val preferencias = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val session = preferencias.getBoolean("Session", false)

        if (session == true) {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

        // Agregar un TextWatcher para verificar cuando se ingrese texto en los campos
        edUsernameL.addTextChangedListener(textWatcher)
        edPasswordL.addTextChangedListener(textWatcher)

        // Inicialmente deshabilitar el botón de ingresar
        btnLogin.isEnabled = false

        // Agregar un OnClickListener al Register para abrir SignUpActivity
        Register.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }





        btnLogin.setOnClickListener {
            val preferencia = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val UserSa = preferencia.getString("User", "xxx").toString()
            val PasswordSa = preferencia.getString("Password", "xxx").toString()

            val editor = preferencia.edit()
            editor.putBoolean("Session", true)
            editor.commit()
            val UserIn = edUsernameL.text.toString()
            val PassIn = edPasswordL.text.toString()

            if (UserIn == UserSa && PassIn == PasswordSa) {
                val intent = Intent(this, SigninActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        fun verify():Boolean{
            var res:Boolean = true
            val username = edUsernameL.text.toString()
            val password = edPasswordL.text.toString()


            if(username.isNullOrEmpty()){
                txtUsernameL.error = "Empty Username"
                res = false
            }else{
                txtUsernameL.error = null
            }
            if(password.isNullOrEmpty()){
                txtPasswordL.error = "Empty Password"
                res = false
            }else{
                txtPasswordL.error = null
            }

            return res
        }

    }


    // TextWatcher para verificar cuando se ingresa texto en los campos de texto
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Verificar si ambos campos tienen texto
            val username = edUsernameL.text.toString().trim()
            val password = edPasswordL.text.toString().trim()
            btnLogin.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        }
    }
}