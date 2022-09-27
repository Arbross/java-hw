package org.example.dto.roledto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateRoleItemDTO {
    @ApiModelProperty(notes = "Role Name", example = "User", required = true)
    private String name;
}
