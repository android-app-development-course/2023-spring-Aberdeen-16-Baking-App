package com.example.BakeMate.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.BakeMate.*
import com.example.BakeMate.databinding.FragmentHomeBinding
import com.example.BakeMate.ui.dashboard.*
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    data class DataBean(
        var imageRes: Int? = null,
        var imageUrl: String? = null,
        var title: String?,
        var viewType: Int
    )

    companion object {

        val testData3: List<DataBean> = listOf(
            DataBean(R.drawable.burger_1, null, null, 1),
            DataBean(R.drawable.cake_1, null, null, 1),
            DataBean(R.drawable.pizza_1, null, null, 1)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val banner: Banner<DataBean, BannerImageAdapter<DataBean>> = binding.banner as Banner<DataBean, BannerImageAdapter<DataBean>>
        banner.setAdapter(object : BannerImageAdapter<DataBean>(testData3) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: DataBean,
                position: Int,
                size: Int
            ) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                    .load(data.imageRes)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }).addBannerLifecycleObserver(this).setBannerRound(100.0F)
            .setIndicatorRadius(500)
            .setIndicator(CircleIndicator(requireContext()))

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //创建各类食物被搜索次数的储存位置
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val bread=prefs.getInt(
            "bread",
            0
        )
        val cookie=prefs.getInt(
            "cookie",
            0
        )
        val cake=prefs.getInt(
            "cake",
            0
        )
        val donut=prefs.getInt(
            "donut",
            0
        )
        val pudding=prefs.getInt(
            "pudding",
            0
        )

        //比较大小
        //第一个list用以储存每个类别被反复问的次数
        val list = mutableListOf(bread, cookie,cake,donut,pudding)
        //第二个list用以纯村每个类别的名称
        val list2= listOf("Bread","Cookie","Cake","Donut","Pudding")
        var k=0
        var flag=-1
        //遍历list，用打擂台的方法找出最大值，同时用flag记住最大值的下标
        for (i in 0 until list.size){
            if(list[i]>k){
                k=list[i]
                flag=i
            }
        }
        //打印
        if(k>0){
            val editText = view.findViewById<EditText>(R.id.editTextTextPersonName2)
            val text = list2[flag]
            val italicTypeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
            //控制字体
            editText.text.clear()
            editText.text.append(text)
            editText.setTypeface(italicTypeface)
        }

        val editor = prefs.edit()
        //清除数据
        val clear: Button=view.findViewById(R.id.button)
        clear.setOnClickListener {
            editor.clear()
            editor.apply()
        }

        //判断搜索的食物
        val search: Button = view.findViewById(R.id.button3)
        val searchEditText: EditText = view.findViewById(R.id.editTextTextPersonName2)

        search.setOnClickListener {
            val searchtext = searchEditText.text.toString()
            if (searchtext.equals("Bread", ignoreCase = true)||searchtext.equals("bread", ignoreCase = true)) {
                //统计

                editor.putInt("bread", bread+1)
                editor.apply()
                //跳转
                val breadFragment = BreadFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, breadFragment)
                    .addToBackStack(null)
                    .commit()
            }

            else if (searchtext.equals("Cookie", ignoreCase = true)||searchtext.equals("cookie", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("cookie", cookie+1)
                editor.apply()
                //跳转
                val cookieFragment = CookieFragement()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, cookieFragment)
                    .addToBackStack(null)
                    .commit()
            }

            else if (searchtext.equals("Cake", ignoreCase = true)||searchtext.equals("cake", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("cake", cake+1)
                editor.apply()
                //跳转
                val cakeFragment = CakeFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, cakeFragment)
                    .addToBackStack(null)
                    .commit()
            }

            else if (searchtext.equals("Donut", ignoreCase = true)||searchtext.equals("donut", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("donut", donut+1)
                editor.apply()
                //跳转
                val donutFragment = DonutFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, donutFragment)
                    .addToBackStack(null)
                    .commit()
            }

            else if (searchtext.equals("Pudding", ignoreCase = true)||searchtext.equals("pudding", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("pudding", pudding+1)
                editor.apply()
                //跳转
                val pizzaFragment = PuddingFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, pizzaFragment)
                    .addToBackStack(null)
                    .commit()
            }

            else if (searchtext.equals("Chives Cookie", ignoreCase = true)||searchtext.equals("chives cookie", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("cookie", cookie+1)
                editor.apply()
                //跳转
                val intent=Intent(activity,ChivesCookiedetail::class.java)
                startActivity(intent)
            }

            else if (searchtext.equals("Ham Floss Cake", ignoreCase = true)||searchtext.equals("ham floss cake", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("cake", cake+1)
                editor.apply()
                //跳转
                val intent=Intent(activity,HamFlossCakedetail::class.java)
                startActivity(intent)
            }

            else if (searchtext.equals("Plaid Bread", ignoreCase = true)||searchtext.equals("plaid bread", ignoreCase = true)) {
                //统计
                val editor = prefs.edit()
                editor.putInt("bread", bread+1)
                editor.apply()
                //跳转
                val intent=Intent(activity,PlaidBreaddetail::class.java)
                startActivity(intent)
            }
            //没找到的菜品
            else {
                //跳转
                val intent=Intent(activity,SearchFail::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}