package com.example.loganodi.cuev2;

public class Action {
    private String actionName, actionId;


    public Action(){

    }

    public Action(String actionName, String actionId){
        this.actionId = actionId;
        this.actionName = actionName;
    }
//GETTERS
    public String getActionName() {

        return actionName;
    }

    public String getActionId() {
        return actionId;
    }
//SETTERS
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}


