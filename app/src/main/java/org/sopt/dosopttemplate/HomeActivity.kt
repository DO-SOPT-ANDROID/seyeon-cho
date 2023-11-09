package org.sopt.dosopttemplate

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
        clickBottomNavigation()
    }

    companion object {
        fun createMyPageFragment(user_id: String?, user_major: String?): MyPageFragment {
            return if (user_id != null && user_major != null) {
                MyPageFragment.newInstance(user_id, user_major)
            } else {
                throw IllegalArgumentException("데이터가 없어요!")
            }
        }
    }

    private fun clickBottomNavigation(){
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home-> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_do_android-> {
                    replaceFragment(DoAndroidFragment())
                    true
                }

                R.id.menu_mypage -> {
                    try {
                        val user_id = intent?.getStringExtra("user_id")
                        val user_major = intent?.getStringExtra("user_major")
                        val myPageFragment = createMyPageFragment(user_id, user_major)
                        replaceFragment(myPageFragment)
                    } catch (e: IllegalArgumentException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()

    }

    private fun setCLICKFAB(){
        binding.fabMain.setOnClickListener {
            Toast.makeText(this, "열리는 중", Toast.LENGTH_SHORT).show()
            eventFAB()
        }
        binding.fabCapture.setOnClickListener {
            Toast.makeText(this, "캡쳐하는 중", Toast.LENGTH_SHORT).show()
        }

        binding.fabShare.setOnClickListener {
            Toast.makeText(this, "공유하는 중", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eventFAB(){
        var openFAB = false
        if (openFAB){
            ObjectAnimator.ofFloat(binding.fabShare, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabCapture, "translationY", 0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.fabShare, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabCapture, "translationY", -200f).apply { start() }
        }
    }
}


