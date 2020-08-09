package com.example.shtil.ibear_test.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import com.example.shtil.ibear_test.Model.Person

class DBHelper (context: Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION )
{
    companion object
    {
        private val DATABASE_NAME = "DATABASE.db"
        private val DATABASE_VERSION = 2

        private val TABLE_NAME = "Person"
        private val COL_ID = "id"
        private val COL_IMG = "img"
        private val COL_NAME = "name"
        private val COL_PHONE = "phone"
        private val COL_EMAIL = "email"

    }
    val allPerson: List <Person>
        get()
        {
            val firstPersons = ArrayList<Person>()
            val db = this.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COL_NAME", null)
            if(cursor.moveToFirst())
            {
                do
                {
                    val person = Person(cursor.getInt(cursor.getColumnIndex(COL_ID)), Uri.parse(cursor.getString(cursor.getColumnIndex(COL_IMG))),cursor.getString(cursor.getColumnIndex(COL_NAME)),cursor.getLong(cursor.getColumnIndex(COL_PHONE)), cursor.getString(cursor.getColumnIndex(COL_EMAIL)))
                    firstPersons.add(person)
                }while (cursor.moveToNext())
            }
            db.close()
            return firstPersons
        }


    override fun onCreate(db: SQLiteDatabase?)
    {
        db!!.execSQL("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_IMG TEXT, $COL_NAME TEXT," + " $COL_PHONE INTEGER, $COL_EMAIL TEXT)")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun addPerson(person: Person)
    {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_IMG, person.img.toString())
        value.put(COL_NAME, person.name)
        value.put(COL_PHONE, person.phone)
        value.put(COL_EMAIL, person.email)
        db.insert(TABLE_NAME, null, value)
        db.close()
    }
    fun updatePerson(person: Person):Int
    {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_IMG, person.img.toString())
        value.put(COL_NAME, person.name)
        value.put(COL_PHONE, person.phone)
        value.put(COL_EMAIL, person.email)
        return db.update(TABLE_NAME,  value, "$COL_ID=?", arrayOf(person.id.toString()))
    }
    fun deletePerson(person: Person)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(person.id.toString()))
        db.close()
    }
}