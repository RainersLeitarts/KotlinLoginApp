package com.example.loginpagekotlin

import android.content.Context
import android.widget.Toast

class Login {
    //Lietotājvārds
    private val user: String = "User123"
    //Parole
    private val password: String = "Password123"

    //Pārbauda vai ievadītie dati sakrīt ar lietotāja datiem
    fun verifyUser(context:Context, user:String, password:String) : Boolean{
        return if (user == this.user){
            if (password == this.password){
                toastMaker(context, "Sveiki $user!")
                true
            }else{
                toastMaker(context, "Ievadi pareizu Paroli!")
                false
            }
        }else{
            toastMaker(context, "Ievadi pareizu Lietotājvārdu!")
            false
        }
    }

    //Paziņojumiem izmanto "Toast"
    private fun toastMaker(context:Context, text:String){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}