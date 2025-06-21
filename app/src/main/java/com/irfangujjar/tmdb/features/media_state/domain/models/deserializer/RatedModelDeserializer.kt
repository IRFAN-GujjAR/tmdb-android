package com.irfangujjar.tmdb.features.media_state.domain.models.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.irfangujjar.tmdb.features.media_state.domain.models.RatedModel
import java.lang.reflect.Type

class RatedModelDeserializer : JsonDeserializer<RatedModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RatedModel? {
        if (json == null) return null
        if (json.isJsonObject) {
            val value = json.asJsonObject.get("value").asDouble
            return RatedModel(value = value)
        } else {
            return RatedModel(value = 0.0)
        }
    }
}