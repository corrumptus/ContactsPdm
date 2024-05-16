package lazarini.lucas.contactspdm.controller

import lazarini.lucas.contactspdm.model.Contact
import lazarini.lucas.contactspdm.persistence.ContactDAO
import lazarini.lucas.contactspdm.persistence.ContactDAOImplSqlite
import lazarini.lucas.contactspdm.ui.MainActivity

class ContactController(mainActivity: MainActivity) {
    private val contactDaoImpl: ContactDAO = ContactDAOImplSqlite(mainActivity)

    fun insertContact(contact: Contact) = contactDaoImpl.createContact(contact)

    fun getContacts() = contactDaoImpl.retrieveContacts()

    fun editContact(contact: Contact) = contactDaoImpl.updateContact(contact)

    fun removeContact(id: Int) = contactDaoImpl.deleteContact(id)
}