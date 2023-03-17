
package com.example.proyectofinal_pescaenaguadulce
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView

class ListadoPecesExistentesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_peces_existentes)
        val listViewPecesExistentes = findViewById<ListView>(R.id.ListViewPecesExistentes)
        val pecesExistentes = arrayListOf<PezExistente>()
        val pez1 = PezExistente("Barbo", "Río", R.drawable.barbo)
        val pez2 = PezExistente("Trucha común", "Río / Lago", R.drawable.truchacomun)
        val pez3 = PezExistente("Trucha arco iris", "Río / Lago", R.drawable.truchaarcoiris)
        val pez4 = PezExistente("Boga", "Río", R.drawable.boga)
        val pez5 = PezExistente("Carpa común", "Embalse", R.drawable.carpacomun)
        val pez6 = PezExistente("Percasol", "Embalse / Río", R.drawable.percasol)
        val pez7 = PezExistente("Lucio", "Embalse", R.drawable.lucio)
        val pez8 = PezExistente("Lucioperca", "Embalse", R.drawable.lucioperca)
        val pez9 = PezExistente("Sirulo", "Embalse", R.drawable.siluro)
        val pez10 = PezExistente("Black bass", "Embalse / Lago", R.drawable.blackbass)
        val pez11 = PezExistente("Carpín", "Embalse", R.drawable.carpin)
        pecesExistentes.add(pez1)
        pecesExistentes.add(pez2)
        pecesExistentes.add(pez3)
        pecesExistentes.add(pez4)
        pecesExistentes.add(pez5)
        pecesExistentes.add(pez6)
        pecesExistentes.add(pez7)
        pecesExistentes.add(pez8)
        pecesExistentes.add(pez9)
        pecesExistentes.add(pez10)
        pecesExistentes.add(pez11)

        val pecesExistentesArrayAdapter = PecesExistentesArrayAdapter(
            applicationContext,
            R.layout.item_list_peces_existentes,
            pecesExistentes
        )
        listViewPecesExistentes.adapter = pecesExistentesArrayAdapter
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Log.i("Info", "Se ha hecho clic en el botón home")
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
    }
}