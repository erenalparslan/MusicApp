package com.erenalparslan.musicapp.data.remote.deserializer

import com.erenalparslan.musicapp.data.remote.models.Track
import com.erenalparslan.musicapp.data.remote.models.Tracks
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class TracksDeserializer : JsonDeserializer<Tracks> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Tracks {
        val monumentElement = json.asJsonObject["track"]
        return if (monumentElement.isJsonArray) {
            Tracks(*context.deserialize<Any>(
                    monumentElement.asJsonArray,
                    Array<Track>::class.java
                ) as Array<Track>
            )
        } else if (monumentElement.isJsonObject) {
            Tracks(
                (context.deserialize<Any>(
                    monumentElement.asJsonObject,
                    Track::class.java
                ) as Track)
            )
        } else {
            throw JsonParseException("Unsupported type of track element")
        }
    }
}
