package com.broadgames.stealthperformancecommunication.session;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Khaizoku on 5/15/2016.
 */
public class Session {
    public static int CLIENT_QUATERBACK = 1;
    public static int CLIENT_PLAYER=2;
    public static int user_session;
    public static Stack<String> message = new Stack<String>();
    public static String clientMessage="";
    public int getUser_session() {
        return Session.user_session;
    }
    public Session(int user_session){
        Session.user_session=user_session;
    }
}
