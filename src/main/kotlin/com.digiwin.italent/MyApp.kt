package com.digiwin.italent

import javafx.scene.control.TextField

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import tornadofx.*

/*
How-tos
    1. test run: $ mvn javafx:run, or $ mvn compile javafx:run
    2. package a far jar: $ mvn compile package
*/
class MyView(

): View() {

    var tenantField: TextField by singleAssign()
    var dateField: TextField by singleAssign()
    var tokenField: TextField by singleAssign()
    var keyField: TextField by singleAssign()

    // init {
    //     this.tokenService = TokenService()
    // }
    val tokenService: TokenService by inject()

    override val root = form {
        fieldset("Input") {
            field("TenantNo") {
                tenantField = textfield()
            }

            field("StartDate") {
                val date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"))
                dateField = textfield(date)
            }
        }
        fieldset("Result") {
            field("Token") {
                tokenField = textfield()
            }
            field("Key") {
                keyField = textfield()
            }
        }
        button("Make key") {
            useMaxWidth = true
            action {
                println("Generating token with tenant=${tenantField.text}, date=${dateField.text}")
                val result = tokenService.generateToken(tenantField.text, dateField.text)
                tokenField.text = result.string("token")
                keyField.text = result.string("key")
            }
        }
    }
}


class MyApp: App(MyView::class)


fun main(args: Array<String>) {
    launch<MyApp>(args)
}