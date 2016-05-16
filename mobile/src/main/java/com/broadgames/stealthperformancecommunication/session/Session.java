package com.broadgames.stealthperformancecommunication.session;

/**
 * Created by Khaizoku on 5/15/2016.
 */
public class Session {
    public static int CLIENT_QUATERBACK = 1;
    public static int CLIENT_PLAYER=2;
    private int user_session;

    public int getUser_session() {
        return user_session;
    }

    public Session(int user_session){
        this.user_session=user_session;

    }
}
