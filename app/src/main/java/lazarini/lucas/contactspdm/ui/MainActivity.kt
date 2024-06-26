package lazarini.lucas.contactspdm.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.R
import lazarini.lucas.contactspdm.adapter.ContactAdapter
import lazarini.lucas.contactspdm.controller.ContactController
import lazarini.lucas.contactspdm.databinding.ActivityMainBinding
import lazarini.lucas.contactspdm.model.Contact
import lazarini.lucas.contactspdm.utils.IntentMapper.CREATE_CONTACT
import lazarini.lucas.contactspdm.utils.IntentMapper.EDIT_CONTACT
import lazarini.lucas.contactspdm.utils.IntentMapper.VIEW_CONTACT

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var newContactActivityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var editContactActivityResultLauncher: ActivityResultLauncher<Intent>

    private var index: Int = -1

    // DATA SOURCE
    private val contactList: MutableList<Contact> by lazy {
        contactController.getContacts()
    }

    // ADAPTER
//    private val listAdapter: ArrayAdapter<Contact> by lazy {
//        ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
//    }

    private val listAdapter: ContactAdapter by lazy {
        ContactAdapter(this, contactList)
    }

    private val contactController: ContactController by lazy {
        ContactController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.toolbarIn.apply {
            setSupportActionBar(this.toolbar)
        }

        newContactActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode != RESULT_OK)
                return@registerForActivityResult

            (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                result.data?.getParcelableExtra(CREATE_CONTACT, Contact::class.java)
            else
                result.data?.getParcelableExtra<Contact>(CREATE_CONTACT)
            )?.let {
                if (!isContactValid(it)) {
                    Toast.makeText(
                        this,
                        "Invalid contact. All the attributes cannot be blank",
                        Toast.LENGTH_LONG
                    ).show()
                    return@let
                }

                val id = contactController.insertContact(it)

                it.id = id

                contactList.add(it)

                listAdapter.notifyDataSetChanged()
            }
        }

        editContactActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode != RESULT_OK)
                return@registerForActivityResult

            (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                result.data?.getParcelableExtra(EDIT_CONTACT, Contact::class.java)
            else
                result.data?.getParcelableExtra<Contact>(EDIT_CONTACT)
            )?.let {
                if (!isContactValid(it)) {
                    Toast.makeText(
                        this,
                        "Invalid contact. All the attributes cannot be blank",
                        Toast.LENGTH_LONG
                    ).show()
                    return@let
                }

                contactList[index] = it
                contactController.editContact(it)

                listAdapter.notifyDataSetChanged()
            }
        }

//        fillList()

        amb.contactsLv.adapter = listAdapter

        registerForContextMenu(amb.contactsLv)

        amb.contactsLv.setOnItemClickListener { _, _, position, _ ->
            Intent(this, ContactActivity::class.java).apply {
                putExtra(VIEW_CONTACT, contactList[position])

                startActivity(this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Intent(this, ContactActivity::class.java).also {
            it.putExtra(CREATE_CONTACT, Contact(id = getNextId()))
            newContactActivityResultLauncher.launch(it)
        }

        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val index = (item.menuInfo as AdapterContextMenuInfo).position

        return when(item.itemId) {
            R.id.editContactMi -> {
                this.index = index

                Intent(this, ContactActivity::class.java).apply {
                    putExtra(EDIT_CONTACT, contactList[index])

                    editContactActivityResultLauncher.launch(this)
                }

                true
            }
            R.id.removeContactMi -> {
                contactController.removeContact(contactList[index].id)

                contactList.removeAt(index)

                listAdapter.notifyDataSetChanged()

                true
            }
            else -> { false }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterForContextMenu(amb.contactsLv)
    }

    private fun getNextId(): Int {
        if (contactList.isEmpty())
            return 1
        return contactList.last().id+1
    }

    private fun isContactValid(contact: Contact): Boolean {
        return contact.name.isNotBlank() &&
            contact.address.isNotBlank() &&
            contact.phone.isNotBlank() &&
            contact.email.isNotBlank()
    }

//    private fun fillList() {
//        for (i in 1..50) {
//            contactList.add(
//                Contact(
//                    i,
//                    "name $i",
//                    "address $i",
//                    "phone $i",
//                    "email $i"
//                )
//            )
//        }
//    }
}