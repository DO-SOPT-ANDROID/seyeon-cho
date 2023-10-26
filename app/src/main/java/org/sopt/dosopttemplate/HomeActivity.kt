package org.sopt.dosopttemplate

import android.os.Bundle
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

                R.id.menu_mypage-> {
                    val user_id = intent?.getStringExtra("user_id")
                    val user_major = intent?.getStringExtra("user_major")

                    if (user_id != null && user_major != null) {
                        replaceFragment(MyPageFragment.newInstance(user_id, user_major))
                    } else {
                        val errorMessage = "데이터가 없어요!."
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
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
}

