package lazarini.lucas.contactspdm.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import lazarini.lucas.contactspdm.R
import lazarini.lucas.contactspdm.databinding.TileContactBinding
import lazarini.lucas.contactspdm.model.Contact

// using viewBinding way 1
// this isn't a good way, because the viewBinding
// isn't being used in its totality
class ContactAdapter(
    context: Context,
    private val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // pegar contact do data source (position)
        val contact = contactList[position]

        // inflar célula se n tiver uma célula reciclada (convertView)
        val contactTileView = if (convertView != null)
            convertView
        else {
//            way 1 to inflate the tile_contact layout view
//            LayoutInflater.from(context)
//            .inflate(R.layout.tile_contact, parent, false)

//            way 2 to inflate the tile_contact layout view
//            (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater)
//            .inflate(R.layout.tile_contact, parent, false)

//            way 3 to inflate the tile_contact layout view
            TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).root
        }

        // preencher a célula
        contactTileView.findViewById<TextView>(R.id.nameTv).text = contact.name
        contactTileView.findViewById<TextView>(R.id.emailTv).text = contact.email

        // retornar a célula
        return contactTileView
    }

//    using holder patter
//    private data class TileContactHolder(
//        val nameTv: TextView,
//        val emailTv: TextView
//    )
}

// using viewBinding way 2
// the best way using the viewBinding, because
// the viewBinding is begin used in its totality
//class ContactAdapter(
//    context: Context,
//    private val contactList: MutableList<Contact>
//): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        // pegar contact do data source (position)
//        val contact = contactList[position]
//
//        // inflar célula se n tiver uma célula reciclada (convertView)
//        var tcb: TileContactBinding? = null
//
//        val contactTileView = if (convertView != null)
//            convertView
//        else {
//            tcb = TileContactBinding.inflate(
//                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
//                parent,
//                false
//            )
//
//            tcb.root.apply {
//                tag = tcb
//            }
//        }
//
//        // preencher a célula
//        (contactTileView.tag as TileContactBinding).nameTv.text = contact.name
//        (contactTileView.tag as TileContactBinding).emailTv.text = contact.email
//
//        // retornar a célula
//        return contactTileView
//    }
//}

// using holder pattern
// its a good choose, but actually its the old way to make it
// (use of findViewById)
//class ContactAdapter(
//    context: Context,
//    private val contactList: MutableList<Contact>
//): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        // pegar contact do data source (position)
//        val contact = contactList[position]
//
//        // inflar célula se n tiver uma célula reciclada (convertView)
//        val contactTileView = if (convertView != null)
//            convertView
//        else {
//            // using holder patter
//            val view = TileContactBinding.inflate(
//                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
//                parent,
//                false
//            ).root.apply {
//                tag = TileContactHolder(
//                    this.findViewById(R.id.nameTv),
//                    this.findViewById(R.id.emailTv)
//                )
//            }
//
//            view
//        }
//
//        // preencher a célula
//        (contactTileView.tag as TileContactHolder).nameTv.text = contact.name
//        (contactTileView.tag as TileContactHolder).emailTv.text = contact.email
//
//        // retornar a célula
//        return contactTileView
//    }
//
//    private data class TileContactHolder(
//        val nameTv: TextView,
//        val emailTv: TextView
//    )
//}