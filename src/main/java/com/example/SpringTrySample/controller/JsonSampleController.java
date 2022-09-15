package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.entity.JsonSampleData;
import org.springframework.web.bind.annotation.*;

/**
 * \@RestController
 * をつけることでメソッドの\@ResponseBodyを省略できる
 *
 * \@ResponseBodyをつけることで戻り値がそのままコンテンツになる
 */
@RestController
@RequestMapping("jsonsample")
public class JsonSampleController {

    /**
     * JsonSampleDateをJsonに変換して返す
     * @return
     */
    @GetMapping
    public JsonSampleData get(){
        return new JsonSampleData("1", "syoki", "taro");
    }

    @PostMapping
    public void post(@RequestBody JsonSampleData jsonSampleData){

    }



}
