package com.example.BakeMate.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BakeMate.PlaidBreaddetail
import com.example.BakeMate.databinding.FragmentDashboardBinding
import com.example.sort1111.Adapter.CategoryAdapter
import com.example.sort1111.Adapter.FastDeliveryAdapter
import com.example.sort1111.Domain.CategoryDomain
import com.example.sort1111.Domain.FastDeliveryDomain

class BreadFragment : androidx.fragment.app.Fragment() {

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

        val fastlistBread: ArrayList<FastDeliveryDomain> = ArrayList<FastDeliveryDomain>()
        fastlistBread.add(FastDeliveryDomain("Plaid Bread","bread_01",8.1,25))
        fastlistBread.add(FastDeliveryDomain("Candy Bread","bread_02",9.5,45))
        fastlistBread.add(FastDeliveryDomain("Cream Buns","bread_03",8.5,35))
        var adapter2 = FastDeliveryAdapter(fastlistBread)
        recyclerViewfastList.adapter = adapter2


        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, category: String, content: String) {
                when (position) {
                    0 -> {
                        adapter2 = FastDeliveryAdapter(fastlistBread)
                    }
                }
                recyclerViewfastList.adapter = adapter2

                adapter2.setOnItemClickListener(object : FastDeliveryAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val fastDelivery = adapter2.fastDeliveryDomains[position]
                        when (fastDelivery.title) {
                            "Plaid Bread" -> {
                                val intent = Intent(requireContext(), PlaidBreaddetail::class.java)
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