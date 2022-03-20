package com.jccsisc.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.jccsisc.mylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.apply {

            imgGraficas.setOnClickListener {
                findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_graphicsFragment)
            }

            imgAgregar.setOnClickListener {
                findNavController(R.id.fragmentContainerView).navigate(R.id.action_homeFragment_to_addFragment)
            }

        }

    }
}