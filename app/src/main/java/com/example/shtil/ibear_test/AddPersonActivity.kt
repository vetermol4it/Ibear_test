package com.example.shtil.ibear_test

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shtil.ibear_test.DBHelper.DBHelper
import com.example.shtil.ibear_test.Model.Person
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_more_info.*
import android.provider.MediaStore
import android.graphics.Bitmap
import java.io.IOException
import android.graphics.BitmapFactory
import android.net.Uri


class AddPersonActivity : AppCompatActivity() {
    val SELECT_PHOTO = 100
    internal lateinit var db: DBHelper
    internal var lstPerson: List<Person> = ArrayList<Person>()
    var img: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)
        db = DBHelper(this)
        lstPerson = db.allPerson
        add_btn_save.setOnClickListener{
            if(add_edt_name.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Введите имя", Toast.LENGTH_SHORT).show()
            }
            else if (add_edt_phone.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Введите номер телефона", Toast.LENGTH_SHORT).show()
            }
            else{
                val person = Person(
                    0,img, add_edt_name.text.toString(),
                    add_edt_phone.text.toString().toLong(), add_edt_email.text.toString()
                )
                db.addPerson(person)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        add_iv.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, SELECT_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            SELECT_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
                img = selectedImage
                val imageStream = contentResolver.openInputStream(selectedImage!!)
                val yourSelectedImage = BitmapFactory.decodeStream(imageStream)
                add_iv.setImageBitmap(yourSelectedImage)
            }
        }

    }

}
