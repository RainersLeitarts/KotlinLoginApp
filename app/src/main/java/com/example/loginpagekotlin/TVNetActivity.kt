package com.example.loginpagekotlin

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_tvnet.*
import java.util.*
import kotlin.system.exitProcess

class TVNetActivity : AppCompatActivity() {

    lateinit var locale: Locale
    private var currentLanguage = "lv"
    private var currentLang: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvnet)

        currentLanguage = intent.getStringExtra(currentLang).toString()

        val btnTvnet = findViewById<Button>(R.id.btn_tvnet)
        val spinner = findViewById<Spinner>(R.id.lang_spinner)

        btnTvnet.setOnClickListener {
            openInBrowser()
        }

        //Šis ir priekšmetu list priekš spinner
        val list = ArrayList<String>()
        list.add(getString(R.string.choose_language))
        list.add("Pусский")
        list.add("English")
        list.add("Latviešu")

        //Array adapter lai izmantotu iepriekš izveidoto list spinner
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Spinner loģika
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                    }
                    1 -> setLocale("ru")
                    2 -> setLocale("en")
                    3 -> setLocale("lv")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    //Šī funkcija atbildīga par linka uzmeklēšanu pārlūkā
    private fun openInBrowser() {
        val url = "https://www.tvnet.lv/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    //Šī funkcija uzstāca locale
    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)

            //refresh intent, kas vatver to pašu activity
            val refresh = Intent(this, TVNetActivity::class.java)
            //Šis flag noņem animāciju, kas radīzies atverot to pašu activity
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            refresh.putExtra(currentLang, localeName)
            //dzēš esošo activity
            finish()
            //atceļ animāciju, kas rastos aizverot esošo activity
            overridePendingTransition(0,0)
            //uzsāk Activitī ar intent kas no saukts par "refresh"
            startActivity(refresh)
        }
    }
}