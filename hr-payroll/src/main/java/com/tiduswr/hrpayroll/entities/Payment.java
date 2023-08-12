package com.tiduswr.hrpayroll.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Payment implements Serializable{

    private String name;
    private Double dailyIncome;
    private Integer days;

    public Double getTotal(){
        return dailyIncome * days;
    }

}
