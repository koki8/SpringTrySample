package com.example.SpringTrySample.device;

public class Keyboard implements Usb{

    @Override
    public String getDeviceName(){
        return "keyboard";
    }

}
