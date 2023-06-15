package com.example.BakeMate.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BakeMate.HamFlossCakedetail
import com.example.BakeMate.databinding.FragmentDashboardBinding
import com.example.sort1111.Adapter.CategoryAdapter
import com.example.sort1111.Adapter.FastDeliveryAdapter
import com.example.sort1111.Domain.CategoryDomain
import com.example.sort1111.Domain.FastDeliveryDomain

class CakeFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val recyclerViewCategoryList = binding.recyclerView
        recyclerViewCategoryList.layoutManager = linearLayoutManager
        val categoryList: ArrayList<CategoryDomain> = ArrayList<CategoryDomain>()
        categoryList.add(CategoryDomain("Bread"))
        categoryList.add(CategoryDomain("Cookie"))
        categoryList.add(CategoryDomain("Cake"))
        categoryList.add(CategoryDomain("Dount"))
        categoryList.add(CategoryDomain("Pudding"))
        val adapter = CategoryAdapter(categoryList)
        recyclerViewCategoryList.adapter = adapter


        val linearLayoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val recyclerViewfastList = binding.view2
        recyclerViewfastList.layoutManager = linearLayoutManager1

        val fastlistCake: ArrayList<FastDeliveryDomain> = ArrayList<FastDeliveryDomain>()
        fastlistCake.add(FastDeliveryDomain("Ham Floss Cake","cake_01",8.1,40))
        fastlistCake.add(FastDeliveryDomain("Cupcake","cake_02",9.5,60))
        var adapter2 = FastDeliveryAdapter(fastlistCake)
        recyclerViewfastList.adapter = adapter2


        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, category: String, content: String) {
                when (position) {
                    2 -> {
                        adapter2 = FastDeliveryAdapter(fastlistCake)
                    }
                }
                recyclerViewfastList.adapter = adapter2

                adapter2.setOnItemClickListener(object : FastDeliveryAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val fastDelivery = adapter2.fastDeliveryDomains[position]
                        when (fastDelivery.title) {
                            "Ham Floss Cake" -> {
                                val intent = Intent(requireContext(), HamFlossCakedetail::class.java)
                                intent.putExtra("title", fastDelivery.title)
                                intent.putExtra("pic", fastDelivery.pic)
                                startActivity(intent)
                            }
                            // 添加其他项的跳转逻辑
                        }
                    }
                })
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}