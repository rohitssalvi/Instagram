package com.thetec.instagram.utils.commen

import com.thetec.instagram.ui.utils.commen.Resource
import com.thetec.instagram.ui.utils.commen.Status
import com.thetec.instagram.ui.utils.commen.ValidationCommen
import com.thetec.instagram.ui.utils.commen.Validator
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo

import org.junit.Test


class ValidatorTest {


    @Test
    fun checkValidEmailID_ToValidate_ReturnSuccess()
    {
        val email="test@gmail.com"
        val emailValidations=Validator.validateEmailField(email)
        assertThat(emailValidations.status,equalTo(Status.SUCCESS))
    }


    @Test
    fun checkValidEmailID_ToValidate_ReturnError()
    {
        val email="rsa123456"
        val emailValidations=Validator.validateEmailField(email)
        assertThat(emailValidations.status,equalTo(Status.ERROR))
    }


    @Test
    fun checkValidName_ToValidate_ReturnSuccess()
    {
        val name="rohit"
        val emailValidations=Validator.validateNameField(name)
        assertThat(emailValidations.status,equalTo(Status.SUCCESS))
    }


    @Test
    fun checkValidName_ToValidate_ReturnError()
    {
        val name=""
        val emailValidations=Validator.validateNameField(name)
        assertThat(emailValidations.status,equalTo(Status.ERROR))
    }
}