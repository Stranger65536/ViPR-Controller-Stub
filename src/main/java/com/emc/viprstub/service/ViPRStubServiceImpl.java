package com.emc.viprstub.service;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.pools.StoragePoolBulkRep;
import com.emc.storageos.model.pools.StoragePoolList;
import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.varray.VirtualArrayList;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class ViPRStubServiceImpl implements ViPRStubService {
    private final ObjectMapper objectMapper;
    private final VirtualArrayList virtualArrayList;
    private final List<StoragePoolRestRep> storagePoolList;

    @Autowired
    public ViPRStubServiceImpl(
            final ResourceLoader resourceLoader,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        this.objectMapper = configureMapper(objectMapper);
        final Resource varraysResource = resourceLoader.getResource("classpath:data/varrays.json");
        virtualArrayList = getVirtualArrayList(varraysResource, objectMapper, propertiesResolver);
        final Resource storagePoolsResource = resourceLoader.getResource("classpath:data/storage-pools.json");
        storagePoolList = getStoragePoolsList(storagePoolsResource, objectMapper, propertiesResolver);
    }

    @Override
    public StoragePoolList getStoragePools() {
        return new StoragePoolList(storagePoolList.stream()
                .map(sp -> new NamedRelatedResourceRep(sp.getId(), sp.getLink(), sp.getName()))
                .collect(Collectors.toList()));
    }

    @Override
    @SuppressWarnings("SuspiciousGetterSetter")
    public VirtualArrayList getVarrays() {
        return virtualArrayList;
    }

    @Override
    public VirtualArrayList getVarrays(final BulkIdParam ids) {
        return new VirtualArrayList(virtualArrayList.getVirtualArrays().stream()
                .filter(va -> ids.getIds().contains(va.getId()))
                .map(i -> {
                    final NamedRelatedResourceRep res = new NamedRelatedResourceRep();
                    res.setId(i.getId());
                    res.setName(i.getName());
                    res.setLink(i.getLink());
                    return res;
                }).collect(Collectors.toList()));
    }

    @Override
    public StoragePoolBulkRep getStoragePools(final BulkIdParam ids) {
        return new StoragePoolBulkRep(storagePoolList.stream()
                .filter(sp -> ids.getIds().contains(sp.getId())).collect(Collectors.toList()));
    }

    private static List<StoragePoolRestRep> getStoragePoolsList(
            final Resource storagePoolsResource,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        final byte[] content = Files.readAllBytes(storagePoolsResource.getFile().toPath());
        final String json = propertiesResolver.resolve(new String(content, UTF_8));
        return objectMapper.readValue(json, new TypeReference<List<StoragePoolRestRep>>() {
        });
    }

    private static ObjectMapper configureMapper(final ObjectMapper objectMapper) {
        final AnnotationIntrospector aiJaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector aiJackson = new JacksonAnnotationIntrospector();
        objectMapper.setAnnotationIntrospector(AnnotationIntrospector.pair(aiJaxb, aiJackson));
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    private static VirtualArrayList getVirtualArrayList(
            final Resource varraysResource,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        final byte[] content = Files.readAllBytes(varraysResource.getFile().toPath());
        final String json = propertiesResolver.resolve(new String(content, UTF_8));
        return objectMapper.readValue(json, VirtualArrayList.class);
    }
}
