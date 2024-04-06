package lazarini.lucas.contactspdm.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.databinding.ActivityContactBinding
import lazarini.lucas.contactspdm.model.Contact
import lazarini.lucas.contactspdm.utils.IntentMapper.CREATE_CONTACT

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

            val resultadoIntent = Intent()

            resultadoIntent.putExtra(CREATE_CONTACT,
                Contact(
                    name = name,
                    address = address,
                    phone = phone,
                    email = email
                )
            )
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }
    }
}