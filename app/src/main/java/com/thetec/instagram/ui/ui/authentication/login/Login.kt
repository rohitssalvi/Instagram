package com.thetec.instagram.ui.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.ui.authentication.register.Register
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.main.MainActivity
import com.thetec.instagram.ui.utils.commen.Status
import kotlinx.android.synthetic.main.login_layout.*

class Login : BaseActivity<LoginViewModel>()
{
    override fun provideLaoutID(): Int = R.layout.login_layout

    override fun setupView(savebundle: Bundle?) {
    ed_email.addTextChangedListener(object : TextWatcher
    {
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

     btn_login.setOnClickListener{
         viewModel.doLogin()
     }

      txt_singn_up.setOnClickListener {
            viewModel.moveSignUpScreen()
        }

    }

    override fun injectDependency(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupObserver() {
        super.setupObserver()

        viewModel.emailField.observe(this, Observer {
            if(ed_email.text.toString()!=it) ed_email.setText(it)
        })

        viewModel.passwordField.observe(this, Observer {
            if(ed_password.text.toString()!= it) ed_password.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when(it.status)
            {
                Status.ERROR -> input_layout_email.error=it.data?.run { getString(this) }
                else -> input_layout_email.isErrorEnabled=false
            }

        })

        viewModel.passwordValidation.observe(this, Observer {
            when(it.status)
            {
                Status.ERROR -> input_layout_password.error=it.data?.run { getString(this) }
                else -> input_layout_password.isErrorEnabled=false
            }
        })

       viewModel.isLogin.observe(this, Observer {
           progress_login.visibility= if(it) View.VISIBLE else View.GONE
       })

      viewModel.lauchAction.observe(this, Observer {
          if(it) {
              startActivity(Intent(this,MainActivity::class.java))
              finish()
          }
      })

     viewModel.moveSignUpScreen.observe(this, Observer {
            if(it)
            {
                startActivity(Intent(this,Register::class.java))
                finish()
            }
        })

      viewModel.isBusttonVisible.observe(this, Observer {

          btn_login.visibility=if(it) View.GONE else View.VISIBLE
      })

    }
}