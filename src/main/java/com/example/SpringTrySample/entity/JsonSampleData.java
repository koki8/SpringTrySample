package com.example.SpringTrySample.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "firstName", "lastName"})
public class JsonSampleData {
    private String id;

    /**
     * @JsonProperty
     * をつけることで対応するプロパティ名を変更できる
     * シリアライズ、デシリアライズともに適用される
     *
     * シリアライズ：インスタンスからJSONへ変換
     * デシリアライズ：JSONからインスタンスへ変換
     */
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
