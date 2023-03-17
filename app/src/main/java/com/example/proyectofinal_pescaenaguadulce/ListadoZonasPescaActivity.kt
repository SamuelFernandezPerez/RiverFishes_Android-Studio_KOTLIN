package com.example.proyectofinal_pescaenaguadulce

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListadoZonasPescaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_zonas_pesca)
        val listViewZonas = findViewById<ListView>(R.id.ListViewZonas)
        val zonas = arrayListOf<Zona>()
        val zona1 = Zona("Lago Bañolas", "Gerona", R.drawable.banolas, R.drawable.map)
        val zona2 = Zona("Río Ebro", "Zaragoza", R.drawable.ebro, R.drawable.map)
        val zona3 = Zona("Río Guadalquivir", "Sevilla", R.drawable.guadalquivir, R.drawable.map)
        val zona4 = Zona("Embalse Guajaraz", "Toledo", R.drawable.guajaraz, R.drawable.map)
        val zona5 = Zona("Lago Ibones", "Huesca", R.drawable.ibones, R.drawable.map)
        val zona6 = Zona("Embalse de Orellana", "Badajoz", R.drawable.orellana, R.drawable.map)
        val zona7 = Zona("Lago Sanabria", "Zamora", R.drawable.sanabria, R.drawable.map)
        val zona8 = Zona("Río Tajo", "Toledo", R.drawable.tajo, R.drawable.map)
        val zona9 = Zona("Río Turia", "Valencia", R.drawable.turia, R.drawable.map)
        val zona10 = Zona("Embalse del Tranco", "Jaén", R.drawable.tranco, R.drawable.map)
        zonas.add(zona1)
        zonas.add(zona2)
        zonas.add(zona3)
        zonas.add(zona4)
        zonas.add(zona5)
        zonas.add(zona6)
        zonas.add(zona7)
        zonas.add(zona8)
        zonas.add(zona9)
        zonas.add(zona10)

        val zonaArrayAdapter =
            ZonaArrayAdapter(applicationContext, R.layout.item_list_zonas_de_pesca, zonas)
        listViewZonas.adapter = zonaArrayAdapter

        listViewZonas.setOnItemClickListener{parent, view, position, id ->
            when(position) {
                0 -> showMap(Uri.parse("geo:42.131609673389384, 2.75688810975286"))
                1 -> showMap(Uri.parse("geo:42.02637981641463, -1.5596562888832015"))
                2 -> showMap(Uri.parse("geo:37.31412696153824, -6.016907563524245"))
                3 -> showMap(Uri.parse("geo:39.795739676468855, -4.086204107581779"))
                4 -> showMap(Uri.parse("geo:42.778011629785, -0.4443181945995198"))
                5 -> showMap(Uri.parse("geo:39.033432558148874, -5.393775139385514"))
                6 -> showMap(Uri.parse("geo:42.123564179109856, -6.71919118689276"))
                7 -> showMap(Uri.parse("geo:39.86181482253618, -4.038434534275491"))
                8 -> showMap(Uri.parse("geo:39.88355646070806, -1.1668113953368164"))
                9 -> showMap(Uri.parse("geo:38.129352905278914, -2.7751037833023346"))
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Log.i("Info", "Se ha hecho clic en el botón home")
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
    }
}


