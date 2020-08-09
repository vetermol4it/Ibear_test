package com.example.shtil.ibear_test


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.shtil.ibear_test.Adapter.ListPersonAdapter
import com.example.shtil.ibear_test.DBHelper.DBHelper
import com.example.shtil.ibear_test.Model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    internal lateinit var db: DBHelper
    internal var lstPerson: List<Person> = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DBHelper(this)
        refresh()
        btn_add.setOnClickListener()
        {
            var intent = Intent(this, AddPersonActivity::class.java)
            startActivity(intent)
            refresh()
        }

    }
    private fun refresh()
    {
        lstPerson = db.allPerson
        val adapter = ListPersonAdapter(this, lstPerson, this)
        list_persons.adapter = adapter
    }
}
