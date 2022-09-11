package com.example.MybatisTrySample.device;

public class Keyboard implements Usb{

    @Override
    public String getDeviceName(){
        return "keyboard";
    }

}
