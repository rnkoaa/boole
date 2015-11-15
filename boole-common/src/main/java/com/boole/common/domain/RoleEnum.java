package com.boole.common.domain;

import com.boole.common.util.StringUtil;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/15/15.
 */
public enum RoleEnum {
    Writer, Director, Cast;

    @Override
    public String toString() {
        return super.toString();
    }

    public static RoleEnum fromString(String role) {
        if (StringUtil.isNullOrEmpty(role))
            return null;

        role = StringUtil.capitalize(role);
        switch (role) {
            case "Writer":
                return Writer;
            case "Director":
                return Director;
            case "Cast":
            case "Actor":
            case "Actress":
            default:
                return Cast;
        }
    }
}
