package ru.bstrdn.voting.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.bstrdn.voting.config.MyUserDetails;
import ru.bstrdn.voting.model.User;
//TODO заменить на @Autenicationprincip..
import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    private SecurityUtil() {
    }

    public static MyUserDetails safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof MyUserDetails) ? (MyUserDetails) principal : null;
    }

    public static MyUserDetails get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authUserId() {
        return get().getUser().getId();
    }
}