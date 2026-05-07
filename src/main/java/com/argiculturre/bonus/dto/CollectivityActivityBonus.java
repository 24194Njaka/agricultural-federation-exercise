package com.argiculturre.bonus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class CollectivityActivityBonus extends CreateCollectivityActivityBonus {
    private String id;
}
