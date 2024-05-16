package lazarini.lucas.contactspdm.persistence

import lazarini.lucas.contactspdm.model.Contact

class ContactDAOImplSqlite: ContactDAO {
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