package com.studywithme.app.present.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.studywithme.app.R
import com.studywithme.app.objects.room.Room

class RoomRecyclerViewAdapter(
    val values: MutableList<Room>,
    private val listener: OnRoomClickListener
) :
    RecyclerView.Adapter<RoomRecyclerViewAdapter.RoomItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_room_list_item, parent, false)
        return RoomItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        holder.bind(values[position], listener, position)
    }

    override fun getItemCount(): Int = values.size

    class RoomItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleView: TextView = view.findViewById(R.id.title)
        private val descriptionView: TextView = view.findViewById(R.id.description)
        private val themeView: TextView = view.findViewById(R.id.theme)

        fun bind(room: Room, listener: OnRoomClickListener, position: Int) {
            titleView.text = room.getTitle()
            themeView.text = room.getTheme()
            descriptionView.text = room.getDescription()
            itemView.setOnClickListener {
                listener.onRoomClick(position)
            }
        }

        override fun toString(): String {
            return super.toString() +
                " title: '" + titleView.text +
                "'\n" + "theme: '" + themeView.text +
                "'\n" + "description: '" + descriptionView.text + "'\n"
        }
    }

    interface OnRoomClickListener {
        fun onRoomClick(position: Int)
    }
}
