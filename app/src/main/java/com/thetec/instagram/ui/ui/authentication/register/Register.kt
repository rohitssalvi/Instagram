package com.thetec.instagram.ui.ui.authentication.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.ui.authentication.login.Login
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.main.MainActivity
import com.thetec.instagram.ui.utils.commen.Status
import kotlinx.android.synthetic.main.sign_up_layout.*

class Register : BaseActivity<RegisterViewModel>(){
    override fun provideLaoutID(): Int = R.layout.sign_up_layout

    override fun setupView(savebundle: Bundle?) {

        ed_email_id.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onEmailChange(s.toString())
            }
        })

        ed_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
               viewModel.onPasswordChange(s.toString())
            }
        })


        ed_name.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onNameChange(s.toString())
            }
        })


        btn_signup.setOnClickListener{

            viewModel.doSignUp()
        }

        txt_login.setOnClickListener {
          //startActivity(Intent(this,Login::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
          viewModel.moveLoginScreen()
        }


    }

    override fun injectDependency(activityComponent: ActivityComponent) {
       activityComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.emailField.observe(this, Observer {
            if(ed_email_id.text.toString()!=it) ed_email_id.setText(it)
        })

        viewModel.passwordField.observe(this, Observer {
            if(ed_password.text.toString()!= it) ed_password.setText(it)
        })

        viewModel.emailFieldValidation.observe(this, Observer {
            when(it.status)
            {
                Status.ERROR -> {
                    inputlayoutEmail.error=it.data?.run { getString(this) }
                }
                else -> { inputlayoutEmail.isErrorEnabled=false
                }
            }

        })


        viewModel.passwordFieldValidation.observe(this, Observer {
            when(it.status)
            {
                Status.ERROR -> {
                    inputLayoutPassword.error=it.data?.run { getString(this) }
                }
                else -> {

                    inputLayoutPassword.isErrorEnabled=false
                }
            }

        })


        viewModel.nameFieldValidation.observe(this, Observer {
            when(it.status)
            {
                Status.ERROR -> {
                    inputLaoutName.isFocusable=true
                    inputLaoutName.error=it.data?.run { getString(this) }
                }
                else -> {
                    inputLaoutName.isErrorEnabled=false
                }
            }

        })

        viewModel.isProgressVisible.observe(this, Observer {
            progressbar.visibility= if(it) View.VISIBLE else View.GONE
        })

        viewModel.signUpCompleteNavigateAction.observe(this, Observer {
            if(it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

      viewModel.moveLoginScreen.observe(this, Observer {
         if(it)
         {
             startActivity(Intent(this,Login::class.java))
             finish()
         }
       })
    }
}