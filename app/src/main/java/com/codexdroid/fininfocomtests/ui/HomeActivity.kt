package com.codexdroid.fininfocomtests.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codexdroid.fininfocomtests.R
import com.codexdroid.fininfocomtests.controller.UsersAdapter
import com.codexdroid.fininfocomtests.databinding.ActivityHomeBinding
import com.codexdroid.fininfocomtests.models.Users
import com.codexdroid.fininfocomtests.utils.AppConstants
import com.codexdroid.fininfocomtests.utils.NoConnectionInterceptor
import com.codexdroid.fininfocomtests.utils.toJson
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


//val list = listOf(
//    Users(id = 1,name = "Gordon Gentry",age = 25,city = "Mumbai"),
//    Users(id = 2,name = "Leonard Perry",age = 24,city = "Amaravati"),
//    Users(id = 3,name = "Nathan Alfaro",age = 28,city = "Visakhapatnam"),
//    Users(id = 4,name = "Austin Lopez",age = 31,city = "Machilipatnam"),
//    Users(id = 5,name = "Brandon Delgado",age = 22,city = "Gandhinagar"),
//    Users(id = 6,name = "Carl Chang",age = 26,city = "Surat"),
//    Users(id = 7,name = "Akshay Figueroa",age = 18,city = "Faridabad"),
//    Users(id = 8,name = "Adam Whitney",age = 44,city = "Kurukshetra"),
//    Users(id = 9,name = "Dan Zamora",age = 55,city = "Hubballi-Dharwad"),
//    Users(id = 10,name = "Charles Wilkerson",age = 61,city = "Nashik"),
//    Users(id = 11,name = "Isaac Farmer",age = 59,city = "Nagpur"),
//    Users(id = 12,name = "Jonathan Copeland",age = 28,city = "Amritsar"))

class HomeActivity : AppCompatActivity() {

    private val dataList by lazy { mutableListOf<Users>() }

    private lateinit var binding:ActivityHomeBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        firebaseDatabase = Firebase.database
        databaseReference = FirebaseDatabase.getInstance("https://fininfocom-tests-default-rtdb.firebaseio.com/").getReference(AppConstants.FIREBASE.DATABASE)

        /** Loading Data to Firebase
        //DONE - 0..11
        list[0..12].apply {
            databaseReference.child("$id").setValue(this)
        }
        **/

        if(NoConnectionInterceptor.isConnectionOn(this)) {
            requestLoadData()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Oops, Device Offline")
                .setMessage("Your device is offline, connect to internet and comback")
                .setPositiveButton("Exit") {d,w ->
                    d.dismiss()
                    finish()
                }.setNegativeButton("Stay") {
                        d,w -> d.dismiss()
                    Snackbar.make(binding.idShimmer,"You feel to exit?",Snackbar.LENGTH_INDEFINITE).apply {
                        setAction("Yes, Exit") {
                            finish()
                        }
                        show()
                    }
                }
                .show()
        }

        requestListener()
    }

    private fun requestLoadData() {
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    val users = it.getValue(Users::class.java)!!
                    Log.d("AXE","Users: ${toJson(users)}")
                    dataList.add(users)
                }

                requestSetAdapter(dataList.shuffled(),AppConstants.BY_RANDOM)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun requestSetAdapter(users: List<Users>,sortBy: String) {

        binding.idSwipe.visibility = View.VISIBLE
        binding.idShimmer.visibility = View.GONE

        UsersAdapter(this,users, sortBy).apply {
            binding.idRecyclerView.adapter = this
        }
    }

    private fun requestListener() {

        binding.idSwipe.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.idSwipe.isRefreshing = false
                requestSetAdapter(dataList.shuffled(), AppConstants.BY_RANDOM)
            },2000L)
        }

        binding.idToolbar.setOnMenuItemClickListener { menu ->
            when(menu.itemId) {
                R.id.id_menu_age -> {
                    requestSetAdapter(dataList.sortedBy { it.age },AppConstants.BY_AGE)
                    true
                }
                R.id.id_menu_city -> {
                    requestSetAdapter(dataList.sortedBy { it.city },AppConstants.BY_CITY)
                    true
                }
                R.id.id_menu_name -> {
                    requestSetAdapter(dataList.sortedBy { it.name },AppConstants.BY_NAME)
                    true
                }
                else -> false
            }
        }
    }
}