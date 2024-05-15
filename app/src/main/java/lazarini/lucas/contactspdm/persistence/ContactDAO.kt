package lazarini.lucas.contactspdm.persistence

import lazarini.lucas.contactspdm.model.Contact

interface ContactDAO {
    fun createContact(contact: Contact): Int
    fun retrieveContacts(): MutableList<Contact>
    fun updateContact(contact: Contact): Int
    fun deleteContact(id: Int): Int
}