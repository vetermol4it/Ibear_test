package com.example.shtil.ibear_test.Model

import android.net.Uri


class Person{
    var id: Int = 0
    var img: Uri? = null
    var name: String? = null
    var phone: Long? = null
    var email: String? = null

    constructor(id: Int,img: Uri?, name: String?, phone: Long?, email: String?){
        this.id = id
        this.img = img
        this.name = name
        this.phone = phone
        this.email = email
    }
}