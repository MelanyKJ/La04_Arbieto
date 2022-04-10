package com.melany.myapplication_lab04_a

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992

const val PARAMETER_EXTRA_NOMBRE_T = "nombre"
const val PARAMETER_EXTRA_APELLIDO_T = "apellido"
const val PARAMETER_EXTRA_CORREO_T = "correo"
const val PARAMETER_EXTRA_TELEFONO_T = "telefono"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sendExplicit(view: android.view.View) {
        val nombre = tvNombre.text.toString()
        val apellido = tvApellido.text.toString()
        val correo = tvCorreo.text.toString()
        val telefono = tvTelefono.text.toString()
        validateInputFields(nombre, apellido, correo, telefono)
        goDetailActivity(nombre, apellido, correo, telefono)

    }


    private fun goDetailActivity(nombre: String, apellido: String, correo: String, telefono: String) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("apellido", apellido)
        intent.putExtra("correo", correo)
        intent.putExtra("telefono", telefono)
        //startActivity(intent)
        startActivityForResult(intent, ACTIVITY_B_REQUEST);
    }

    private fun validateInputFields(nombre: String, apellido: String, correo: String, telefono: String) {
        if (nombre.isBlank() && apellido.isBlank() && correo.isBlank() && telefono.isBlank())
            return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "requestCode:$requestCode")
        Log.d(TAG, "resultCode:$resultCode")
        Log.d(TAG, "data:" + android.R.attr.data)

        when (requestCode) {
            ACTIVITY_A_REQUEST -> Log.d(TAG, "Regresamos del Activity A")
            ACTIVITY_B_REQUEST -> {
                Log.d(TAG, "VISTA PRINCIPAL")
                if (resultCode === RESULT_OK) {
                    val valor: String = data?.extras?.getString("valor").toString()
                    Log.d(TAG, "valor: $valor")
                }
                val extras = data?.extras

                if (extras != null) {
                    if (extras.get(PARAMETER_EXTRA_NOMBRE_T) != null) {
                        tvNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE_T))
                    }

                    if (extras.get(PARAMETER_EXTRA_APELLIDO_T) != null) {
                        tvApellido.setText(extras.getString(PARAMETER_EXTRA_APELLIDO_T))
                    }

                    if (extras.get(PARAMETER_EXTRA_CORREO_T) != null) {
                        tvCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO_T))
                    }

                    if (extras.get(PARAMETER_EXTRA_TELEFONO_T) != null) {
                        tvTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO_T))
                    }
                }
            }
        }

    }


    //LLAMADA EN UN TELEFONO
    fun calls_phone(view: android.view.View) {
        val telefono = tvTelefono.text.toString()
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent);
    }

    //MENSAJE A WSP
    fun MsgWhatsApp(view: View?) {
        try {
            val telefono = tvTelefono.text.toString()
            val text1 = "Hola soy"
            val nombre = tvNombre.text.toString()
            val text2 = " \uD83D\uDE4B\u200D♀ te mando este mensaje desde Android Studio ❤"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=$text1$nombre$text2")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // MENSAJE SMS
    fun SmsMessage(view: android.view.View) {
        val nombre = tvNombre.text.toString()
        val telefono = tvTelefono.text.toString()
        val uri = Uri.parse("smsto:$telefono")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra(
            "sms_body",
            "Hola soy $nombre " + "\uD83D\uDE4B\u200D♀ te mando este mensaje desde Android Studio ❤"
        )
        startActivity(it)
    }
}

