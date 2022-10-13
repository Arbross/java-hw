package org.example.dto.roledto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleItemDTO extends CreateRoleItemDTO {
    @ApiModelProperty(notes = "Role Id", example = "*id*", required = true)
    private int id;
}
