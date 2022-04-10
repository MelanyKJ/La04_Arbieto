package com.melany.myapplication_lab04_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import android.os.Bundle

const val PARAMETER_EXTRA_NOMBRE = "nombre"
const val PARAMETER_EXTRA_APELLIDO = "apellido"
const val PARAMETER_EXTRA_CORREO = "correo"
const val PARAMETER_EXTRA_TELEFONO = "telefono"

class DetailActivity : AppCompatActivity() {
    override fun onCreate(saveInstanceState:Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NOMBRE) != null) {
                edtName.setText(extras.getString(PARAMETER_EXTRA_NOMBRE))
            }

            if (extras.get(PARAMETER_EXTRA_APELLIDO) != null) {
                edtLastName.setText(extras.getString(PARAMETER_EXTRA_APELLIDO))
            }

            if (extras.get(PARAMETER_EXTRA_CORREO) != null) {
                edtEmail.setText(extras.getString(PARAMETER_EXTRA_CORREO))
            }

            if (extras.get(PARAMETER_EXTRA_TELEFONO) != null) {
                edtPhone.setText(extras.getString(PARAMETER_EXTRA_TELEFONO))
            }
        }
    }

    fun SaveButton(view:android.view.View){
        val nombre = edtName.text.toString()
        val apellido = edtLastName.text.toString()
        val correo = edtEmail.text.toString()
        val telefono = edtPhone.text.toString()
        val intent= Intent(this,MainActivity::class.java)
        intent.putExtra("nombre",nombre)
        intent.putExtra("apellido",apellido)
        intent.putExtra("correo",correo)
        intent.putExtra("telefono",telefono)
        setResult(RESULT_OK,intent)
        finish()
    }
}