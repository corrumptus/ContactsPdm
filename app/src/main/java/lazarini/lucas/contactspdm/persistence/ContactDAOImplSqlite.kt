package lazarini.lucas.contactspdm.persistence

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import lazarini.lucas.contactspdm.model.Contact

class ContactDAOImplSqlite(context: Context): ContactDAO {
    companion object {
        const val CONTACT_DATABASE_FILE = "contacts"
    }

    private val contactSqliteDatabase: SQLiteDatabase = context.openOrCreateDatabase(
        CONTACT_DATABASE_FILE,
        MODE_PRIVATE,
        null
    )

    override fun createContact(contact: Contact): Int {
        TODO("Not yet implemented")
    }

    override fun retrieveContacts(): MutableList<Contact> {
        TODO("Not yet implemented")
    }

    override fun updateContact(contact: Contact): Int {
        TODO("Not yet implemented")
    }

    override fun deleteContact(id: Int): Int {
        TODO("Not yet implemented")
    }
}