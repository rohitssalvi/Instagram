package com.thetec.instagram.ui.utils.commen

import android.content.res.Resources
import com.thetec.instagram.R
import java.util.regex.Pattern

object Validator {

    private val EMAIL_REGEX=Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")
    private val NAME_REGEX=Pattern.compile("[a-zA-Z]+")

    private const val PASSWORD_LENGTH=6


    fun validateLoginFields(email: String?, password: String?): List<ValidationCommen> =
        ArrayList<ValidationCommen>().apply {
            when {
                email.isNullOrBlank() ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.error(R.string.email_id_empty)))
                !EMAIL_REGEX.matcher(email).matches() ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.error(R.string.valid_email_id)))
                else ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.success()))
            }
            when {
                password.isNullOrBlank() ->
                    add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.error(R.string.password_field_empty)))
                password.length < PASSWORD_LENGTH ->
                    add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.error(R.string.valid_password)))
                else -> add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.success()))
            }
        }


    fun validateSignFields(name:String?,email: String?, password: String?): List<ValidationCommen> =
        ArrayList<ValidationCommen>().apply {
            when {
                name.isNullOrBlank() ->
                    add(ValidationCommen(ValidationCommen.Field.NAME, Resource.error(R.string.name_field_empty)))
                !NAME_REGEX.matcher(name).matches() ->
                    add(ValidationCommen(ValidationCommen.Field.NAME, Resource.error(R.string.name_field_valid)))
                else ->
                    add(ValidationCommen(ValidationCommen.Field.NAME, Resource.success()))
            }
            when {
                email.isNullOrBlank() ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.error(R.string.email_id_empty)))
                !EMAIL_REGEX.matcher(email).matches() ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.error(R.string.valid_email_id)))
                else ->
                    add(ValidationCommen(ValidationCommen.Field.EMAIL, Resource.success()))
            }
            when {
                password.isNullOrBlank() ->
                    add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.error(R.string.password_field_empty)))
                password.length < PASSWORD_LENGTH ->
                    add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.error(R.string.valid_password)))
                else -> add(ValidationCommen(ValidationCommen.Field.PASSWORD, Resource.success()))
            }
        }


    fun validateEmailField(email:String):Resource<Int>{
     when
     {
         email.isNullOrBlank()-> {
             return Resource.error(R.string.email_id_empty)
         }
          !EMAIL_REGEX.matcher(email).matches()-> {
              return Resource.error(R.string.valid_email_id)
          }
         else ->
             return  Resource.success()
     }
    }

    fun validatePasswordField(password:String):Resource<Int>{
        when
        {
            password.isNullOrBlank()->{
                return  Resource.error(R.string.password_field_empty)
            }
            password.length < PASSWORD_LENGTH ->{
                return  Resource.error(R.string.valid_password)
            }
            else ->
                return   Resource.success()
        }
    }


    fun validateNameField(name:String):Resource<Int>{
        when
        {
            name.isNullOrBlank()-> {
                return Resource.error(R.string.name_field_empty)
            }
            !NAME_REGEX.matcher(name).matches() -> {
                return Resource.error(R.string.name_field_valid)
            }
            else ->
                return Resource.success()
        }
    }





}

data class ValidationCommen(val field:Field,val resource: Resource<Int>){

    enum class Field{
       EMAIL,
        PASSWORD,
        NAME
    }

}
