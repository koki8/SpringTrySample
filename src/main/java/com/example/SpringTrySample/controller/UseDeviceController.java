package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.device.Usb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usb")
public class UseDeviceController {

    /**
     * Usbにインスタンスをインジェクションしている
     */
    private final Usb device;
    UseDeviceController(Usb device) {
        this.device = device;
    }

    @GetMapping("useDevice")
    public String UseDevice(){
        String deviceName = device.getDeviceName();
        return deviceName;
    }
}
