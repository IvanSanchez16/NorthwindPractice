package com.coppel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDTO {

    private short status;
    private String mensaje;
    private Object data;

    public ResponseDTO() {
        mensaje = "";
    }
}
