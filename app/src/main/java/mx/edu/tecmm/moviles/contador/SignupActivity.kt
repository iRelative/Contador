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
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

    lateinit var txtUsername: TextInputLayout
    lateinit var txtPassword: TextInputLayout
    lateinit var txtPasswordA: TextInputLayout
    lateinit var txtName: TextInputLayout
    lateinit var txtDegree: TextInputLayout
    lateinit var txtPhone: TextInputLayout

    lateinit var btnCreateAccount: Button

    lateinit var edUsername : EditText
    lateinit var edPassword : EditText
    lateinit var edPasswordA : EditText
    lateinit var edName : EditText
    lateinit var edDegree : EditText
    lateinit var edPhone : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets

        }

        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        txtPasswordA = findViewById(R.id.txtPasswordA)
        txtName = findViewById(R.id.txtName)
        txtDegree = findViewById(R.id.txtDegree)
        txtPhone = findViewById(R.id.txtPhone)

        btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)

        edUsername = findViewById(R.id.edUsername)
        edPassword = findViewById(R.id.edPassword)
        edPasswordA = findViewById(R.id.edPasswordA)
        edName = findViewById(R.id.edName)
        edDegree = findViewById(R.id.edDegree)
        edPhone = findViewById(R.id.edPhone)

        val usernameet = edUsername.text.toString()
        val passwordet = edPassword.text.toString()
        val passwordAet = edPasswordA.text.toString()
        val nameet = edName.text.toString()
        val degreeet = edDegree.text.toString()
        val phoneet = edPhone.text.toString()

        fun verify():Boolean{
            var res:Boolean = true
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()
            val passwordA = edPasswordA.text.toString()
            val name = edName.text.toString()
            val degree = edDegree.text.toString()
            val phone = edPhone.text.toString()

            if(username.isNullOrEmpty()){
                txtUsername.error = "Empty Username"
                res = false
            }else{
                txtUsername.error = null
            }
            if(password.isNullOrEmpty()){
                txtPassword.error = "Empty Password"
                res = false
            }else{
                txtPassword.error = null
            }
            if(passwordA.isNullOrEmpty()){
                txtPasswordA.error = "Empty Confirmation Password"
                res = false
            }else{
                txtPasswordA.error = null
            }
            if(password != passwordA){
                txtPasswordA.error  = "Passwords do not match"
                res = false
            }else{
                txtPasswordA.error  = null
            }
            if(name.isNullOrEmpty()){
                txtName.error = "Empty Name"
                res = false
            }else{
                txtName.error = null
            }
            if(phone.isNullOrEmpty()){
                txtPhone.error = "Empty Phone"
                res = false
            }else{
                txtPhone.error = null
            }
            if(degree.isNullOrEmpty()){
                txtDegree.error = "Empty Degree"
                res = false
            }else{
                txtDegree.error = null
            }
            return res
        }

        btnCreateAccount.setOnClickListener {
            if (verify()) {
                val usernameet = edUsername.text.toString()
                val passwordet = edPassword.text.toString()
                val nameet = edName.text.toString()
                val degreeet = edDegree.text.toString()
                val phoneet = edPhone.text.toString()

                // Todos los campos están completos, puedes guardar los datos en las preferencias compartidas
                val preferencia = getSharedPreferences("Data", Context.MODE_PRIVATE)
                val editor = preferencia.edit()
                editor.putString("User", usernameet)
                editor.putString("Password", passwordet)
                editor.putString("Name", nameet)
                editor.putString("Degree", degreeet)
                editor.putString("Phone", phoneet)
                editor.putBoolean("Session", false)
                editor.apply()

                Toast.makeText(this,"Datos Guardados Exitosamente",Toast.LENGTH_LONG).show()

                finish()
            }
        }




    }
}