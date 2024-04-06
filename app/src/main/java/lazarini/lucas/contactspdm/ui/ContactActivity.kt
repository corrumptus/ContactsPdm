package lazarini.lucas.contactspdm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.databinding.ActivityContactBinding

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
    }
}