package com.example.chatapp.model;

import com.example.chatapp.data.Navigation;

public final class NavigationModel {
    private final Navigation navigate = new Navigation();

    public NavigationModel(){
        addScreen("Home");
    }
    public String getCurrentScreen(){
        return navigate.currentScreen;
    }
    public void setCurrentScreen(String current){
        navigate.currentScreen = current;
    }
    public void addScreen(String screen){
        navigate.navStack.add(screen);
        setCurrentScreen(screen);
    }
    public void popScreen(){
        String last = navigate.navStack.get(navigate.navStack.size());
        navigate.navStack.removeIf((str) -> str == last);
        setCurrentScreen(navigate.navStack.get(navigate.navStack.size()));
    }
}

