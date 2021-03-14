package ru.bstrdn.voting.util;

import ru.bstrdn.voting.model.Restaurant;

//todo realize
public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static Restaurant check(){

        return null;
    }
}
