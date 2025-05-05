import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.example.contactsapp.Contact

@SuppressLint("Range")
fun getContacts(context: Context): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        ContactsContract.Contacts.DISPLAY_NAME + " ASC"
    )

    cursor?.use {
        while (it.moveToNext()) {
            val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val id = it.getLong(it.getColumnIndex(ContactsContract.Contacts._ID))
            contacts.add(Contact(id, name, phone))
        }
    }
    return contacts
}