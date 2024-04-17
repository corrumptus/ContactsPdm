package lazarini.lucas.contactspdm.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.databinding.ActivityContactBinding
import lazarini.lucas.contactspdm.model.Contact
import lazarini.lucas.contactspdm.utils.IntentMapper.CREATE_CONTACT
import lazarini.lucas.contactspdm.utils.IntentMapper.EDIT_CONTACT
import lazarini.lucas.contactspdm.utils.IntentMapper.VIEW_CONTACT

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        acb.toolbarIn.apply {
            setSupportActionBar(this.toolbar)
        }

        acb.enviaBt.setOnClickListener {
            val name = acb.nameEt.text.toString()
            val address = acb.addressEt.text.toString()
            val phone = acb.phoneEt.text.toString()
            val email = acb.emailEt.text.toString()

            val resultIntent = Intent()

            if (intent.getParcelableExtra<Contact>(CREATE_CONTACT) != null)
                resultIntent.putExtra(CREATE_CONTACT,
                    intent.getParcelableExtra<Contact>(CREATE_CONTACT)?.apply {
                        this.name = name
                        this.address = address
                        this.phone = phone
                        this.email = email
                    }
                )
            else
                resultIntent.putExtra(EDIT_CONTACT,
                    intent.getParcelableExtra<Contact>(EDIT_CONTACT)?.apply {
                        this.name = name
                        this.address = address
                        this.phone = phone
                        this.email = email
                    }
                )

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        intent.getParcelableExtra<Contact>(VIEW_CONTACT)?.apply {
            acb.enviaBt.visibility = View.GONE

            acb.nameEt.setText(this.name)
            acb.nameEt.isEnabled = false

            acb.addressEt.setText(this.address)
            acb.addressEt.isEnabled = false

            acb.phoneEt.setText(this.phone)
            acb.phoneEt.isEnabled = false

            acb.emailEt.setText(this.email)
            acb.emailEt.isEnabled = false
        }

        intent.getParcelableExtra<Contact>(EDIT_CONTACT)?.apply {
            acb.enviaBt.text = "Editar"
            acb.nameEt.setText(this.name)
            acb.addressEt.setText(this.address)
            acb.phoneEt.setText(this.phone)
            acb.emailEt.setText(this.email)
        }
    }
}