package com.example.softwareqaandtesting

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwareqaandtesting.register.RegisterFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRegisterFragment()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount == 0){
            showQuitDialog()
        }
    }

    private fun showQuitDialog() {
        val dialogBuilder = MaterialAlertDialogBuilder(this)
        dialogBuilder.setCancelable(true)
            .setMessage("Are you sure you want to quit?")
            .setPositiveButton("Yes") { _, _ ->
                this.finish()
            }
            .setNegativeButton("No") { d, _ ->
                d.cancel()
                addRegisterFragment()
            }
            .show()
    }

    private fun addRegisterFragment(){
        supportFragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(R.id.frame_layout, RegisterFragment.newInstance())
            fragmentTransaction.addToBackStack(Constants.REGISTER_FRAGMENT_TAG)
            fragmentTransaction.commit()
        }
    }
}