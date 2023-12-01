package domain;

public class LoggedUser {
    private static User user;
    LoggedUser(){
        user = null;
    }

    public static void setUser(User u){
        user =  u;
    }

    public static User getUser(){
        return user;
    }
}
