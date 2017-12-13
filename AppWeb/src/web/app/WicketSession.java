package web.app;

import org.apache.wicket.Session;

import web.app.storage.db.User;

public class WicketSession {
    
    private static final Session session = Session.get();
    
    private static final String USER = "USER";
    
    public static void saveUser(User user) {
        session.setAttribute(USER, user);
    }
    
    public static User getUser() {
        return (User) session.getAttribute(USER);
    }
    
    public static void removeUser() {
        session.removeAttribute(USER);
    }
}