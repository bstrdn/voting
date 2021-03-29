package ru.bstrdn.voting.util;

import ru.bstrdn.voting.model.HasId;
import ru.bstrdn.voting.model.Restaurant;
import ru.bstrdn.voting.util.exception.IllegalRequestDataException;

//todo realize
public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }
}
