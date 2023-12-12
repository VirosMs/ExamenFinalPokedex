package com.example.examenfinal.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Clase que implementa JsonDeserializer para deserializar los efectos de un movimiento.
 * <p>
 * Esta clase se utiliza para personalizar la deserialización de los efectos de un movimiento desde un objeto JSON.
 */
public class EfectosDeserializer implements JsonDeserializer<String> {

    /**
     * Método que deserializa los efectos de un movimiento desde un objeto JSON.
     *
     * @param json    El JsonElement que se va a deserializar.
     * @param typeOfT El tipo específico de objeto al que se va a deserializar el JsonElement.
     * @param context Contexto para la deserialización.
     * @return Una cadena que representa los efectos del movimiento.
     * @throws JsonParseException si json no es del tipo esperado.
     */
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        StringBuilder str = new StringBuilder();
        JsonArray array = json.getAsJsonArray();

        for (JsonElement element : array) {
            str.append(element.getAsJsonObject().get("short_effect").getAsString());
        }

        return str.toString();
    }
}
