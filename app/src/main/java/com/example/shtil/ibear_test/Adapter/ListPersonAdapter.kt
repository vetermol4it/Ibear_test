package com.example.shtil.ibear_test.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.shtil.ibear_test.Model.Person
import com.example.shtil.ibear_test.MoreInfoActivity
import com.example.shtil.ibear_test.R
import kotlinx.android.synthetic.main.row_layout.view.*
import java.lang.Exception


class ListPersonAdapter(internal var activity: Activity, internal var lstPerson: List<Person>, var context: Context): BaseAdapter(){

    internal var inflater: LayoutInflater
    init{
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.row_layout,null)
        rowView.name_tv.text = lstPerson[position].name.toString()
        rowView.phone_tv.text = lstPerson[position].phone.toString()
        rowView.image_iv.setImageURI(lstPerson[position].img)
        if(rowView.image_iv.drawable == null) {
            rowView.image_iv.setImageResource(R.drawable.defaultimage)
        }
        rowView.setOnClickListener(){
            if(lstPerson[position].name!=null) {
                var intent = Intent(context, MoreInfoActivity::class.java)
                intent.putExtra("pos", position)
                startActivity(context, intent, null)
            }
        }
        return  rowView
    }

    override fun getItem(position: Int): Any {
        return  lstPerson[position]
    }

    override fun getItemId(position: Int): Long {
        return lstPerson[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstPerson.size
    }

}