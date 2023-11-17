package org.sopt.dosopttemplate.Home

import android.animation.ObjectAnimator
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.Manifest
import org.sopt.dosopttemplate.DoAndroid.DoAndroidFragment
import org.sopt.dosopttemplate.mypage.MyPageFragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.user.UserFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var openFAB = false
    private val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
        clickBottomNavigation()
        setCLICKFAB()
        checkManageExternalStoragePermission()
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

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(UserFragment())
                    true
                }

                R.id.menu_do_android -> {
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

    private fun setCLICKFAB() {
        binding.fabMain.setOnClickListener {
            eventFAB()
        }
        binding.fabCapture.setOnClickListener {
            takeScreenshot()
        }

        binding.fabShare.setOnClickListener {
            Toast.makeText(this, "공유하는 중", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eventFAB() {
        if (openFAB) {
            ObjectAnimator.ofFloat(binding.fabShare, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabCapture, "translationY", 0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.fabShare, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabCapture, "translationY", -200f).apply { start() }
        }
        openFAB = !openFAB
    }

    private fun checkManageExternalStoragePermission() {
        val managePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (managePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun takeScreenshot() {
        val rootView = window.decorView.rootView
        val screenShot: File? = captureScreen(rootView)
    }
    private fun captureScreen(view: View): File? {
        val screenBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(screenBitmap)
        view.draw(canvas)

        if (screenBitmap.byteCount == 0) {
            return null
        }

        val filename = "Profilescreenshot.png"
        val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename)

        try {
            val output = FileOutputStream(file)
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
            output.close()

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return file
    }
}

