package com.studywithme.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.studywithme.app.R
import com.studywithme.app.adapters.FilterRecyclerViewAdapter
import com.studywithme.app.adapters.FilterRecyclerViewAdapter.OnFilterClickListener
import com.studywithme.app.adapters.RoomRecyclerViewAdapter
import com.studywithme.app.adapters.RoomRecyclerViewAdapter.OnRoomClickListener
import com.studywithme.app.data.RoomItem
import com.studywithme.app.databinding.FragmentRoomListBinding
import com.studywithme.app.dummy.DummyRoomContent

class RoomListFragment : Fragment(), OnFilterClickListener, OnRoomClickListener {

    private var _binding: FragmentRoomListBinding? = null
    private val binding get() = _binding!!
    private val roomList: MutableList<RoomItem> = mutableListOf()
    private val filterList: MutableList<String> = mutableListOf()
    private val recyclerAdapter = RoomRecyclerViewAdapter(roomList, this)
    private val filterAdapter = FilterRecyclerViewAdapter(filterList, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.fragment_rooms_title)
        setAdapters()
        setOnClickListeners()
    }

    private fun setAdapters() {
        filterList.addAll(listOf("Math", "Bath", "Kek", "Pek"))
        binding.filterList.adapter = filterAdapter
        roomList.addAll(DummyRoomContent.ITEMS)
        binding.roomList.adapter = recyclerAdapter
    }

    private fun setOnClickListeners() {
        binding.fabCreate.setOnClickListener {
            openFragment(CreateRoomFragment())
        }
        binding.menu.setOnClickListener {
            openFragment(MenuFragment())
        }
        binding.searchIcon.setOnClickListener {
            openFragment(SearchFragment())
        }
        val themes = resources.getStringArray(R.array.hints_for_theme).toList()
        val adapter = ArrayAdapter(binding.root.context, R.layout.them_list_item, themes)
        binding.menuAutocomplete.setAdapter(adapter)
        binding.menuAutocomplete.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Выводим выпадающий список
                binding.menuAutocomplete.showDropDown()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFilterClick(position: Int) {
        Toast.makeText(context, "Filter $position clicked", Toast.LENGTH_SHORT).show()
        filterList.removeAt(position)
        filterAdapter.notifyItemRemoved(position)
    }

    override fun onRoomClick(position: Int) {
        Toast.makeText(context, "Room $position clicked", Toast.LENGTH_SHORT).show()
        openFragment(MembersFragment())
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment, null)
            .show(fragment)
            .hide(this)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
