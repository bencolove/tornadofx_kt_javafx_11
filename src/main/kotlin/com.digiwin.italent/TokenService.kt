package com.digiwin.italent

import javafx.beans.property.SimpleStringProperty

import javax.json.JsonObject

import tornadofx.*



// class GenerateRequest: JsonModel {
//     val tenantProperty = SimpleStringProperty(),
//     val dateProperty = SimpleStringProperty()
//     var tenant by tenantProperty
//     var date by dateProperty
//     // var tenant: String
//     // var date: String 

//     override fun toJSON(json: JsonBuilder) {
//         with(json) {
//             add("tenant", tenant)
//             add("date", date)
//         }
//     }
// }

// class GenerateResult: JsonModel {
//     val tokenProperty  = SimpleStringProperty(),
//     val keyProperty  = SimpleStringProperty()
//     var token by tokenProperty
//     var key by keyProperty

//     // var token: String
//     // var key: String

//     override fun updateModel(json: JsonObject) {
//         with(json) {
//             token = string("token")
//             key = string("key")
//         }
//     }
// }


class TokenService: Controller() {

    val api: Rest by inject()

    init {
        api.baseURI = "http://52.187.58.216/api/italentservice/"
    }

    public fun generateToken(tenant: String, date: String): JsonObject {
        val payload = JsonBuilder().add("tenant", tenant).add("date", date).build()
        return api.post("generate", payload).one()
    }
}