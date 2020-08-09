package com.example.shtil.ibear_test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.shtil.ibear_test.DBHelper.DBHelper
import com.example.shtil.ibear_test.Model.Person
import kotlinx.android.synthetic.main.activity_more_info.*

class MoreInfoActivity : AppCompatActivity()
{
    private val SELECT_PHOTO = 100
    internal lateinit var db: DBHelper
    internal var lstPerson: List<Person> = ArrayList<Person>()
    private var img: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)
        val position = intent.getIntExtra("pos",0)

        db = DBHelper(this)
        lstPerson = db.allPerson
        img = lstPerson[position].img

        info_iv.setImageURI(img)
        if(info_iv.drawable == null)
        {
            info_iv.setImageResource(R.drawable.defaultimage)
        }
        info_edt_name.setText(lstPerson[position].name)
        info_edt_email.setText(lstPerson[position].email)
        info_edt_phone.setText(lstPerson[position].phone.toString())

        info_btn_save.setOnClickListener()
        {
            if(info_edt_name.text.isNullOrEmpty())
            {
                Toast.makeText(applicationContext, "Введите имя", Toast.LENGTH_SHORT).show()
            }
            else if (info_edt_phone.text.isNullOrEmpty())
            {
                Toast.makeText(applicationContext, "Введите номер телефона", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val person = Person(lstPerson[position].id, img,info_edt_name.getText().toString(),info_edt_phone.text.toString().toLong(), info_edt_email.text.toString())
                db.updatePerson(person)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        info_btn_delete.setOnClickListener()
        {
           val person = Person(lstPerson[position].id,img, info_edt_name.text.toString(), info_edt_phone.text.toString().toLong(), info_edt_email.text.toString())
            db.deletePerson(person)
            startActivity(Intent(this, MainActivity::class.java))
        }

        info_iv.setOnClickListener()
        {
            val photoPickerIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, SELECT_PHOTO)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode)
        {
            SELECT_PHOTO -> if (resultCode == Activity.RESULT_OK)
            {
                img = imageReturnedIntent!!.data
                info_iv.setImageURI(img)
            }
        }
    }
}
