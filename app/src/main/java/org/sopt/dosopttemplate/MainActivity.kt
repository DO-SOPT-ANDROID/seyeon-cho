package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            val inputTextId = intent.getStringExtra("user_id")
            val inputTextMajor = intent.getStringExtra("user_major")
            val inputTextName = intent.getStringExtra("user_name")

            val idTextView = binding.mypageTextId
            val majorTextView = binding.mypageTextMajor
            val nameTextView = binding.mypageTextName

            idTextView.text = "$inputTextId"
            majorTextView.text = "$inputTextMajor"
            nameTextView.text = "$inputTextName"
        }

}