package com.example.a111111

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class WT_PersonalSetActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var etBirthDate: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnSubmit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wt_personalinfo2)

        // 初始化控件
        etName = findViewById(R.id.et_name)
        rgGender = findViewById(R.id.rg_gender)
        rbMale = findViewById(R.id.rb_male)
        rbFemale = findViewById(R.id.rb_female)
        etBirthDate = findViewById(R.id.et_birth_date)
        etPhone = findViewById(R.id.et_phone)
        etAddress = findViewById(R.id.et_address)
        btnSubmit = findViewById(R.id.btn_submit)

        // 提交按钮点击事件
        btnSubmit.setOnClickListener {
            savePersonalInfo()
        }
    }

    /**
     * 保存个人信息
     */
    private fun savePersonalInfo() {
        // 获取各个输入框的值
        val name = etName.text.toString().trim { it <= ' ' }
        val birthDate = etBirthDate.text.toString().trim { it <= ' ' }
        val phone = etPhone.text.toString().trim { it <= ' ' }
        val address = etAddress.text.toString().trim { it <= ' ' }
        var gender = ""
        val checkedRadioButtonId = rgGender.checkedRadioButtonId
        if (checkedRadioButtonId == rbMale.id) {
            gender = "男"
        } else if (checkedRadioButtonId == rbFemale.id) {
            gender = "女"
        }

        // 校验输入的值是否合法
        if (name.isEmpty()) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
            return
        }
        if (birthDate.isEmpty()) {
            Toast.makeText(this, "请输入出生日期", Toast.LENGTH_SHORT).show()
            return
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, "请输入联系电话", Toast.LENGTH_SHORT).show()
            return
        }
        if (address.isEmpty()) {
            Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: 将个人信息保存到本地或服务器
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show()
        finish()
    }
}