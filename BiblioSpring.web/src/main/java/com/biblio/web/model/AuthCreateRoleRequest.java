package com.biblio.web.model;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest(List<String> roleListName) {
}
