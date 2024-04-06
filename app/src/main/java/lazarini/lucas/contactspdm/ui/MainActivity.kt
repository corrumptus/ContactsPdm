package lazarini.lucas.contactspdm.ui

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.R
import lazarini.lucas.contactspdm.databinding.ActivityMainBinding
import lazarini.lucas.contactspdm.model.Contact

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // DATA SOURCE
    private val contactList: MutableList<Contact> = mutableListOf()

//    // ADAPTER
    private val listAdapter: ArrayAdapter<Contact> by lazy {
        ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.toolbarIn.apply {
            setSupportActionBar(this.toolbar)
        }

        fillList()

        amb.contactsLv.adapter = listAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun fillList() {
        for (i in 1..50) {
            contactList.add(
                Contact(
                    i,
                    "name $i",
                    "address $i",
                    "phone $i",
                    "email $i"
                )
            )
        }
    }
}