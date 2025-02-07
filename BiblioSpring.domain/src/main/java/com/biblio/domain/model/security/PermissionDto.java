package com.biblio.domain.model.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionDto {

    private Long id;
    private String name;

}
