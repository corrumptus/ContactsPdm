package lazarini.lucas.contactspdm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.contactspdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
    }
}