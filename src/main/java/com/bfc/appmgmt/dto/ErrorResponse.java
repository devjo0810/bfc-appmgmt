package com.bfc.appmgmt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.bfc.appmgmt.dto
 * fileName       : ErrorResponse
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@Data
@Builder
public class ErrorResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object content;
    private String message;
    private String code;
}
