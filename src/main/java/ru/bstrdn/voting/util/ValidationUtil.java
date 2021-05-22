package ru.bstrdn.voting.util;

import ru.bstrdn.voting.model.HasId;
import ru.bstrdn.voting.util.exception.IncorrectDataException;

public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IncorrectDataException(bean + " must be new (id=null)");
        }
    }

}
