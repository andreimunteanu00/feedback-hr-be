package com.feedback.mnt.util;

import com.feedback.mnt.dto.user.UserSecurityDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static String getEmailCurrentUser() {
        UserSecurityDTO user = (UserSecurityDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getEmail();
    }

}
