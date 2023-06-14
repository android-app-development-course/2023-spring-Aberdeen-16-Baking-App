package com.example.BakeMate.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.BakeMate.MainActivity
import com.example.BakeMate.Register
import com.example.BakeMate.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    var username: String? = null
    var phone: String? = null


    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        val textView3: TextView = binding.textView3
        textView3.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val imageView3: ImageView = binding.imageView3
        imageView3.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        val button: Button = binding.button2
        button.setOnClickListener{
            val intent = Intent(activity, Register::class.java)
            startActivityForResult(intent, 1)
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                username = data?.getStringExtra("username")
                val editTextTextPersonName = binding.textView7
                editTextTextPersonName.setText(username)
                val editTextTextPersonName1 = binding.textView
                editTextTextPersonName1.setText(username)
                phone = data?.getStringExtra("phone")
                val editTextTextPhone = binding.textView11
                editTextTextPhone.setText(phone)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}