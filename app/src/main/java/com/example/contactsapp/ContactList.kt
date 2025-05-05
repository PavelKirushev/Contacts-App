import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactsapp.Contact
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.contactsapp.ContactItem
import com.example.contactsapp.LetterHeader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactList(contacts: Map<Char, List<Contact>>, onContactClick: (String) -> Unit) {
    val totalContacts = remember(contacts) {
        contacts.values.sumOf { it.size }
    }
    LazyColumn(contentPadding = WindowInsets.navigationBars.asPaddingValues()) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(8.dp)) {
                Text(text = "Контакты", style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onPrimary))
            }
        }

        contacts.forEach { (initial, contactsForLetter) ->
            stickyHeader {
                LetterHeader(initial.toString())
            }
            items(contactsForLetter) { contact ->
                ContactItem(contact, onContactClick)
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Всего контактов: $totalContacts", style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimary))
            }
        }
    }

}