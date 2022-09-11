package com.example.MybatisTrySample.config;

import com.example.MybatisTrySample.device.Keyboard;
import com.example.MybatisTrySample.device.Mouse;
import com.example.MybatisTrySample.device.Usb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  Usbに接続するdeviceを設定
 */
@Configuration
public class DeviceSelectConfig {

    /**
     * Keyboardを使う場合はこのメソッドを有効にする
     * @return
     */
    @Bean
    public Usb useSelectKeyboard(){
        return new Keyboard();
    }

//    /**
//     * Mouseを使う場合にはこのメソッドを有効にする
//     * @return
//     */
//    @Bean
//    public Usb useSelectMouse(){
//        return new Mouse();
//    }
}
