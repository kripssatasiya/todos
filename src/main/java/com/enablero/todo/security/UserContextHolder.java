package com.enablero.todo.security;

public class UserContextHolder {
    public static final ThreadLocal<String> currentUserEmail = new ThreadLocal<>();


    public static void setCurrentUserEmail(String email){
        currentUserEmail.set(email);
    }
    public static String getCurrentUserEmail(){
        return currentUserEmail.get();
    }
    public static void clear() {
        currentUserEmail.remove();
    }
}
