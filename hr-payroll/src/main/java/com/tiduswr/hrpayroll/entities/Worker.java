package com.tiduswr.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Worker implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private Double dailyIncome;
}
