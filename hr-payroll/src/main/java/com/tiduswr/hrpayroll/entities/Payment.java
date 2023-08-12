package com.tiduswr.hrpayroll.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Payment implements Serializable{

    private String name;
    private Double dailyIncome;
    private Integer days;
}
