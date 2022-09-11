package com.example.MybatisTrySample.device;

public class Mouse implements Usb{

    @Override
    public String getDeviceName(){
        return "Mouse";
    }
}
