package com.broadgames.stealthperformancecommunication.session;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Khaizoku on 5/15/2016.
 */
public class Session {
    public static int CLIENT_QUATERBACK = 1;
    public static int CLIENT_PLAYER=2;
    private int user_session;
    public static Stack<String> message = new Stack<String>();
    public static String clientMessage = "";
    public static int index = 0;
    public int getUser_session() {
        return user_session;
    }

    public Session(int user_session){
        this.user_session=user_session;
        index = 0;
    }
}
