package com.collective.federation.dto;

import com.collective.federation.entity.CollectivityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectivityResponse extends CollectivityEntity {
    private LocalDate authorizationDate;
    private Integer memberCount;
}
