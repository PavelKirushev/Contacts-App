package com.example.contactsapp

fun getSortedContacts(contacts: List<Contact>): Map<Char, List<Contact>> {
    return contacts
        .sortedBy { it.name }
        .groupBy { it.name.first().uppercaseChar() }
}
