package com.adja.apps.mohamednagy.myapplication;

import android.content.Intent;

/**
 * Created by Mohamed Nagy on 1/22/2018.
 */

public class MyReminderTask {

    public static final String MY_TASK_ACTION_NAME = "my action name";

    /**
     * To check If this action is the required action or not.
     * @param action    Incoming action name
     */
    public void executeTask(String action){
        if(MY_TASK_ACTION_NAME.equals(action)){
            response();
        }
    }

    public void response(){
        // Code run when the action is done.
    }
}
