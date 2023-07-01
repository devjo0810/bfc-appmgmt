package com.bfc.appmgmt.dto;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.bfc.appmgmt.dto
 * fileName       : ApiResponse
 * author         : joyousang
 * date           : 2023/07/01
 * description    :
 */
@Data
@Builder
public class ApiResponse {
    private Object content;
}
