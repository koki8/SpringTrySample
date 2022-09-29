package com.example.SpringTrySample.config;

import com.example.SpringTrySample.filter.ParameterChangeConfirmationFilter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import javax.servlet.Filter;

@Profile("!dev3")  //application-dev3.ymlが有効になっているときは動作しない
@Component
@Data
@ConfigurationProperties(prefix = "variable") // application.ymlのvariable.parameterを読み込む
public class SelectParameterConfig {

    private String parameter = "hogehoge"; // application.ymlのvariable.parameterがインジェクションされる

    @Bean
    public Filter parameterChangeConfirmationFilter(){
        return new ParameterChangeConfirmationFilter(parameter);
    }

}
