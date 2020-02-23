package com.lakmalz.lznewsapplication.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.lakmalz.lznewsapplication.R
import com.lakmalz.lznewsapplication.data.models.User
import com.lakmalz.lznewsapplication.ui.base.BaseFragment
import com.lakmalz.lznewsapplication.util.CustomKeyWords
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    private lateinit var mUser: User
    private val mViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    companion object {
        fun newInstance() = ProfileFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeUserData()
    }

    private fun observeUserData() {
        mViewModel.getUserLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                btn_register.text = getString(R.string.logout)
                txt_title.text = it.userName
                edt_user_name.visibility = View.GONE
                Toast.makeText(
                    context,
                    getString(R.string.message_success_registration),
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    private fun initUI() {

        val keyWords = listOf(
            CustomKeyWords.BITCOIN,
            CustomKeyWords.APPLE, CustomKeyWords.EARTHQUAKE, CustomKeyWords.ANIMAL
        )

        keyWords.forEachIndexed { index, word ->
            addChips(index, word, mViewModel.getUserData().selectedKeyword)
        }

        mUser = mViewModel.getUserData()
        if (mUser.userName != null) {
            btn_register.text = getString(R.string.logout)
            edt_user_name.visibility = View.GONE
            txt_title.text = mUser.userName
        }

        btn_register.setOnClickListener { v ->
            if ((v as Button).text == getString(R.string.logout)) {
                btn_register.text = getString(R.string.register)
                edt_user_name.visibility = View.VISIBLE
                txt_title.text = getString(R.string.email)
                mViewModel.logoutUser()
                Toast.makeText(context, getString(R.string.message_logout), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val userName = edt_user_name.text.trim().toString()
            val selectedChip =
                chip_group_key_words.findViewById<Chip>(chip_group_key_words.checkedChipId)
            if (selectedChip == null) {
                Toast.makeText(
                    context,
                    getString(R.string.message_empty_keyword),
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            } else if (userName.isNullOrEmpty()) {
                Toast.makeText(context, getString(R.string.message_empty_email), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            mViewModel.saveUserName(userName)
        }
    }


    private fun addChips(index: Int, title: String, selectedKeyword: String?) {
        val chip = Chip(context)
        chip.id = index
        chip.text = title
        chip.chipBackgroundColor =
            ContextCompat.getColorStateList(context!!, R.color.chip_type_selected)
        chip.setTextColor(ContextCompat.getColorStateList(context!!, R.color.chip_selected_text))
        chip.isFocusable = true
        chip.isClickable = true
        chip.textSize = 14.0f
        chip.isCheckable = true
        if (selectedKeyword != null) {
            if (selectedKeyword.equals(title)) {
                chip.isChecked = true
            }
        }
        chip.setOnClickListener { view ->
            mViewModel.saveSelectedKeyword((view as Chip).text.toString())
        }
        chip.checkedIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_selected)
        chip.isCloseIconVisible = false
        chip_group_key_words.addView(chip)
    }
}