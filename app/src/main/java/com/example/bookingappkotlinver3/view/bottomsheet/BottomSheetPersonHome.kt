package com.example.bookingappkotlinver3.view.bottomsheet

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.example.bookingappkotlinver3.R
import com.example.bookingappkotlinver3.constant.AppConstant
import com.example.bookingappkotlinver3.constant.MySharedPreferences
import com.example.bookingappkotlinver3.databinding.BottomsheetEditPersonHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetPersonHome constructor(context: Context, private val callback: Callback) :
    BottomSheetDialog(context), View.OnClickListener {
    private var _binding: BottomsheetEditPersonHomeBinding? = null
    private val binding get() = _binding!!
    private var countPersonText = 0
    private var countPersonChildrenText = 0
    private var countRoomText = 0
    private var ageText = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window!!.setGravity(Gravity.BOTTOM)
        _binding = BottomsheetEditPersonHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.close.setOnClickListener {
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.login.setOnClickListener {
            val countPerson = binding.person.text.toString().trim().toInt()
            val countPersonChildren = binding.personChildren.text.toString().trim().toInt()
            val age = binding.countAge.text.toString().trim().toInt()
            val countRoomCallback = binding.countRoom.text.toString().trim().toInt()
            callback.onCLickSum(countPerson, countPersonChildren, countRoomCallback, age)
            dismiss()
        }

        binding.downPersonChildren.alpha = 0.4f
        binding.downPersonChildren.isEnabled = false
        binding.downAge.alpha = 0.4f
        binding.downAge.isEnabled = false
        binding.downCountRoom.alpha = 0.4f
        binding.downCountRoom.isEnabled = false
        binding.downPerson.alpha = 0.4f
        binding.downPerson.isEnabled = false

        binding.downPerson.setOnClickListener(this)
        binding.downPersonChildren.setOnClickListener(this)
        binding.downCountRoom.setOnClickListener(this)
        binding.downAge.setOnClickListener(this)
        binding.upPerson.setOnClickListener(this)
        binding.upPersonChildren.setOnClickListener(this)
        binding.upCountRoom.setOnClickListener(this)
        binding.upAge.setOnClickListener(this)

        initDataCache()
    }

    interface Callback {
        fun onCLickSum(person: Int, children: Int, countRoom: Int, age: Int);
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.upPerson -> {
                countPersonText += 1
                binding.person.text = countPersonText.toString()
                checkCountPerson()
            }

            R.id.downPerson -> {
                countPersonText -= 1
                binding.person.text = countPersonText.toString()
                checkCountPerson()
            }

            R.id.upPersonChildren -> {
                countPersonChildrenText += 1
                binding.personChildren.text = countPersonChildrenText.toString()
                checkCountChildren()
            }

            R.id.downPersonChildren -> {
                countPersonChildrenText -= 1
                binding.personChildren.text = countPersonChildrenText.toString()
                checkCountChildren()
            }

            R.id.upCountRoom -> {
                countRoomText += 1
                binding.countRoom.text = countRoomText.toString()
                checkCountRoom()
            }

            R.id.downCountRoom -> {
                countRoomText -= 1
                binding.countRoom.text = countRoomText.toString()
                checkCountRoom()
            }

            R.id.downAge -> {
                ageText -= 1
                binding.countAge.text = ageText.toString()
                checkAge()
            }

            R.id.upAge -> {
                ageText += 1
                binding.countAge.text = ageText.toString()
                checkAge()
            }
        }
    }

    private fun checkCountPerson() {
        if (countPersonText > 1) {
            binding.downPerson.alpha = 1f
            binding.downPerson.isEnabled = true
        } else {
            binding.downPerson.alpha = 0.4f
            binding.downPerson.isEnabled = false
        }

        if (binding.countRoom.text.toString().toInt() > binding.person.text.toString().toInt()) {
            countRoomText = binding.person.text.toString().toInt()
            binding.countRoom.text = countRoomText.toString()
            checkCountRoom()
        }
        if (countPersonText < 50) {
            binding.upPerson.alpha = 1f
            binding.upPerson.isEnabled = true
        } else {
            binding.upPerson.alpha = 0.4f
            binding.upPerson.isEnabled = false
        }
    }

    private fun checkCountChildren() {
        if (countPersonChildrenText > 0) {
            binding.downPersonChildren.alpha = 1f
            binding.downPersonChildren.isEnabled = true
        } else {
            binding.downPersonChildren.alpha = 0.4f
            binding.downPersonChildren.isEnabled = false
        }

        if (countPersonChildrenText < 50) {
            binding.upPersonChildren.alpha = 1f
            binding.upPersonChildren.isEnabled = true
        } else {
            binding.upPersonChildren.alpha = 0.4f
            binding.upPersonChildren.isEnabled = false
        }
    }

    private fun checkCountRoom() {
        if (countRoomText > 1) {
            binding.downCountRoom.alpha = 1f
            binding.downCountRoom.isEnabled = true
        } else {
            binding.downCountRoom.alpha = 0.4f
            binding.downCountRoom.isEnabled = false
        }

        if (countRoomText < 16) {
            binding.upCountRoom.alpha = 1f
            binding.upCountRoom.isEnabled = true
        } else {
            binding.upCountRoom.alpha = 0.4f
            binding.upCountRoom.isEnabled = false
        }

        if (binding.countRoom.text.toString().toInt() > binding.person.text.toString().toInt()) {
            countPersonText = binding.countRoom.text.toString().toInt()
            binding.person.text = countPersonText.toString()
            checkCountPerson()
        }
    }


    private fun checkAge() {
        if (ageText > 1) {
            binding.downAge.alpha = 1f
            binding.downAge.isEnabled = true
        } else {
            binding.downAge.alpha = 0.4f
            binding.downAge.isEnabled = false
        }

        if (ageText < 17) {
            binding.upAge.alpha = 1f
            binding.upAge.isEnabled = true
        } else {
            binding.upAge.alpha = 0.4f
            binding.upAge.isEnabled = false
        }
    }

    private fun initDataCache() {
        countRoomText = MySharedPreferences.getInstance(context)
            .getInt(AppConstant.SHAREDPREFERENCES_USER_COUNT_ROOM, 2).apply {
            binding.countRoom.text = this.toString()
        }
        countPersonText = MySharedPreferences.getInstance(context  ).getInt(AppConstant.SHAREDPREFERENCES_USER_COUNT_PERSON,
            2).apply {
            binding.person.text = this.toString()
        }

        countPersonChildrenText = MySharedPreferences.getInstance(context).getInt(  AppConstant.SHAREDPREFERENCES_USER_COUNT_CHILDREN,
            2).apply {
            binding.personChildren.text = this.toString()
        }
        ageText = MySharedPreferences.getInstance(context).getInt(AppConstant.SHAREDPREFERENCES_USER_AGE_CHILDREN,
            1).apply {
            binding.countAge.text = this.toString()
        }
        checkAge()
        checkCountPerson()
        checkCountRoom()
        checkCountChildren()
    }
}

