package edu.event.utils;

import edu.event.entities.User;

public class Sessions {

    private static User loggedInUser = null;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Sessions.loggedInUser = loggedInUser;
    }

}
