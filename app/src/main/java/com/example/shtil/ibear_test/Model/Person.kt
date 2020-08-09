package com.example.shtil.ibear_test.Model

import android.net.Uri


class Person
{
    var id: Int = 0
    set(value) {
        field = if(value<0) 0 else value
    }
    var img: Uri? = null
    var name: String
    var phone: Long
    set(value) {
        field = if (value < 0) -1 * value else if (value > 999999999999999) 999999999999999 else value
    }
    var email: String? = null

    constructor(id: Int,img: Uri?, name: String, phone: Long, email: String?)
    {
        this.id = if(id<0) 0 else id
        this.img = img
        this.name = name
        this.phone = if(phone<0) -1*phone else if(phone>999999999999999) 999999999999999 else phone
        this.email = email
    }
    constructor(img: Uri?, name: String, phone: Long, email: String?)
    {
       // this.id = 0
        this.img = img
        this.name = name
        this.phone = if(phone<0) -1*phone else if(phone>999999999999999) 999999999999999 else phone
        this.email = email
    }
}