/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
public class ViPRStubController {
    private static final ObjectMapper JSON = new ObjectMapper();

    @GetMapping("/login")
    public ResponseEntity<String> login() throws JsonProcessingException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-SDS-AUTH-TOKEN", "token");
        return new ResponseEntity<>(JSON.writeValueAsString(singletonMap("login", "ok")), headers, HttpStatus.OK);
    }
}
