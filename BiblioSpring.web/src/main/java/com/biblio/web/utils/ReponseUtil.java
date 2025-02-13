package com.biblio.web.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ReponseUtil {

    private ReponseUtil(){
        throw new UnsupportedOperationException("it is not possible to instantiate the utility class");
    }
    public static ResponseEntity<byte[]> createByteArrayResponse(byte[] bytes) {
        // Configurar los encabezados antes de crear la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}