package br.fiap.gff.users.infra.util;

import com.google.gson.Gson;

public class Jsonfy {
    private Jsonfy() {}

    public static String parse(Object o) {
        return new Gson().toJson(o);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
}
