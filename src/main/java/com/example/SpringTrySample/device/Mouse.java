package com.example.SpringTrySample.device;

public class Mouse implements Usb{

    @Override
    public String getDeviceName(){
        return "Mouse";
    }
}
