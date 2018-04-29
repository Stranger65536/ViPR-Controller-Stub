/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub.controller;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.pools.StoragePoolBulkRep;
import com.emc.storageos.model.pools.StoragePoolList;
import com.emc.storageos.model.varray.VirtualArrayList;
import com.emc.viprstub.service.ViPRStubService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
public class ViPRStubController {
    private final ObjectMapper objectMapper;
    private final ViPRStubService stubService;

    @Autowired
    public ViPRStubController(
            final ObjectMapper objectMapper,
            final ViPRStubService stubService) {
        this.objectMapper = objectMapper;
        this.stubService = stubService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() throws JsonProcessingException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-SDS-AUTH-TOKEN", "token");
        return new ResponseEntity<>(objectMapper.writeValueAsString(
                singletonMap("login", "ok")),
                headers, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/vdc/varrays")
    public ResponseEntity<VirtualArrayList> getVirtualArrays() {
        return new ResponseEntity<>(stubService.getVarrays(), HttpStatus.OK);
    }

    @PostMapping("/vdc/varrays/bulk")
    public ResponseEntity<VirtualArrayList> getVirtualArraysByIds(@RequestBody final BulkIdParam ids) {
        return new ResponseEntity<>(stubService.getVarrays(ids), HttpStatus.OK);
    }

    @GetMapping("/vdc/storage-pools")
    public ResponseEntity<StoragePoolList> getStoragePools() {
        return new ResponseEntity<>(stubService.getStoragePools(), HttpStatus.OK);
    }

    @PostMapping("/vdc/storage-pools/bulk")
    public ResponseEntity<StoragePoolBulkRep> getStoragePoolsByIds(@RequestBody final BulkIdParam ids) {
        return new ResponseEntity<>(stubService.getStoragePools(ids), HttpStatus.OK);
    }
}
