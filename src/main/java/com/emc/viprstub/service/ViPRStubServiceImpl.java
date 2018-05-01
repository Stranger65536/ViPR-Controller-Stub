/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub.service;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.RelatedResourceRep;
import com.emc.storageos.model.pools.StoragePoolBulkRep;
import com.emc.storageos.model.pools.StoragePoolList;
import com.emc.storageos.model.pools.StoragePoolRestRep;
import com.emc.storageos.model.systems.StorageSystemRestRep;
import com.emc.storageos.model.varray.VirtualArrayList;
import com.emc.storageos.model.varray.VirtualArrayRestRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolBulkRep;
import com.emc.storageos.model.vpool.BlockVirtualPoolParam;
import com.emc.storageos.model.vpool.BlockVirtualPoolRestRep;
import com.emc.storageos.model.vpool.NamedRelatedVirtualPoolRep;
import com.emc.storageos.model.vpool.VirtualPoolList;
import com.emc.storageos.model.vpool.VirtualPoolPoolUpdateParam;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class ViPRStubServiceImpl implements ViPRStubService {
    private static final Pattern VPID = Pattern.compile("vpid");

    private final ObjectMapper objectMapper;
    private final List<VirtualArrayRestRep> virtualArrayList;
    private final List<StoragePoolRestRep> storagePoolList;
    private final List<StorageSystemRestRep> storageSystemList;
    private final List<BlockVirtualPoolRestRep> virtualPools;
    private final String virtualPoolTemplate;

    @Autowired
    public ViPRStubServiceImpl(
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        this.objectMapper = configureMapper(objectMapper);
        this.virtualPools = new ArrayList<>(10);
        virtualArrayList = parseVirtualArrays("data/varrays.json", objectMapper, propertiesResolver);
        storagePoolList = parseStoragePools("data/storage-pools.json", objectMapper, propertiesResolver);
        storageSystemList = parseStorageSystems("data/storage-systems.json", objectMapper, propertiesResolver);
        virtualPoolTemplate = getVirtualPoolTemplate("data/virtual-pool.json", propertiesResolver);
    }

    @Override
    @SuppressWarnings("SuspiciousGetterSetter")
    public VirtualArrayList getVarrays() {
        return new VirtualArrayList(virtualArrayList.stream()
                .map(sp -> new NamedRelatedResourceRep(sp.getId(), sp.getLink(), sp.getName()))
                .collect(Collectors.toList()));
    }

    @Override
    public VirtualArrayList getVarrays(final BulkIdParam ids) {
        return new VirtualArrayList(virtualArrayList.stream()
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
    public StoragePoolList getStoragePools() {
        return new StoragePoolList(storagePoolList.stream()
                .map(sp -> new NamedRelatedResourceRep(sp.getId(), sp.getLink(), sp.getName()))
                .collect(Collectors.toList()));
    }

    @Override
    public StoragePoolBulkRep getStoragePools(final BulkIdParam ids) {
        return new StoragePoolBulkRep(storagePoolList.stream()
                .filter(sp -> ids.getIds().contains(sp.getId())).collect(Collectors.toList()));
    }

    @Override
    public StoragePoolRestRep getStoragePool(final String id) throws URISyntaxException {
        final URI uri = new URI(id);
        return storagePoolList.stream().filter(sp -> sp.getId().equals(uri)).findFirst().orElse(null);
    }

    @Override
    public StorageSystemRestRep getStorageSystem(final String id) throws URISyntaxException {
        final URI uri = new URI(id);
        return storageSystemList.stream().filter(sp -> sp.getId().equals(uri)).findFirst().orElse(null);
    }

    @Override
    public VirtualPoolList getVirtualPools() {
        return new VirtualPoolList(virtualPools.stream()
                .map(sp -> new NamedRelatedVirtualPoolRep(sp.getId(), sp.getLink(), sp.getName(), sp.getType()))
                .collect(Collectors.toList()));
    }

    @Override
    public BlockVirtualPoolRestRep createVirtualPool(final BlockVirtualPoolParam param) throws IOException {
        if (virtualPools.stream().anyMatch(vp -> vp.getName().equals(param.getName()))) {
            throw new RestClientException("Duplicate virtual pool name " + param.getName());
        }
        final String id = UUID.randomUUID().toString();
        final String poolString = VPID.matcher(virtualPoolTemplate).replaceAll(id);
        final BlockVirtualPoolRestRep virtualPool = objectMapper.readValue(
                poolString, BlockVirtualPoolRestRep.class);
        virtualPool.setName(param.getName());
        virtualPools.add(virtualPool);
        return virtualPool;
    }

    @Override
    public BlockVirtualPoolBulkRep getVirtualPools(final BulkIdParam ids) {
        return new BlockVirtualPoolBulkRep(virtualPools.stream()
                .filter(sp -> ids.getIds().contains(sp.getId())).collect(Collectors.toList()));
    }

    @Override
    public BlockVirtualPoolRestRep assignPools(
            final String poolId,
            final VirtualPoolPoolUpdateParam param)
            throws URISyntaxException {
        final URI poolURI = new URI(poolId);
        final BlockVirtualPoolRestRep virtualPool = virtualPools.stream()
                .filter(sp -> sp.getId().equals(poolURI))
                .findFirst().orElseThrow(() -> new RestClientException("No such pool exists " + poolId));
        final List<StoragePoolRestRep> pools = param.getStoragePoolAssignmentChanges().getAdd().getStoragePools()
                .stream()
                .map(id -> {
                    final StoragePoolRestRep poolRestRep;
                    try {
                        poolRestRep = getStoragePool(id);
                    } catch (Exception e) {
                        throw new RestClientException("Invalid id " + id, e);
                    }
                    if (poolRestRep == null) {
                        throw new RestClientException("No such pool with id " + id);
                    }
                    return poolRestRep;
                }).collect(Collectors.toList());
        pools.forEach(pool -> virtualPool.getAssignedStoragePools().add(
                new RelatedResourceRep(pool.getLink().getLinkRef(), pool.getLink())));

        return virtualPool;
    }

    private static ObjectMapper configureMapper(final ObjectMapper objectMapper) {
        final AnnotationIntrospector aiJaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector aiJackson = new JacksonAnnotationIntrospector();
        objectMapper.setAnnotationIntrospector(AnnotationIntrospector.pair(aiJaxb, aiJackson));
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final String[] ignorableFieldNames = {"matchedStoragePools", "invalidMatchedStoragePools"};
        final FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter properties by name",
                        SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        objectMapper.setFilterProvider(filters);
        return objectMapper;
    }

    @SuppressWarnings({"AnonymousInnerClass", "SameParameterValue"})
    private static List<VirtualArrayRestRep> parseVirtualArrays(
            final String path,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        try (final InputStream is = ViPRStubServiceImpl.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader isr = new InputStreamReader(is, UTF_8);
             final BufferedReader br = new BufferedReader(isr)
        ) {
            final String json = propertiesResolver.resolve(br.lines().collect(Collectors.joining("\n")));
            return objectMapper.readValue(json, new TypeReference<List<VirtualArrayRestRep>>() {
            });
        }
    }

    @SuppressWarnings({"AnonymousInnerClass", "SameParameterValue"})
    private static List<StoragePoolRestRep> parseStoragePools(
            final String path,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        try (final InputStream is = ViPRStubServiceImpl.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader isr = new InputStreamReader(is, UTF_8);
             final BufferedReader br = new BufferedReader(isr)
        ) {
            final String json = propertiesResolver.resolve(br.lines().collect(Collectors.joining("\n")));
            return objectMapper.readValue(json, new TypeReference<List<StoragePoolRestRep>>() {
            });
        }
    }

    @SuppressWarnings({"AnonymousInnerClass", "SameParameterValue"})
    private static List<StorageSystemRestRep> parseStorageSystems(
            final String path,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        try (final InputStream is = ViPRStubServiceImpl.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader isr = new InputStreamReader(is, UTF_8);
             final BufferedReader br = new BufferedReader(isr)
        ) {
            final String json = propertiesResolver.resolve(br.lines().collect(Collectors.joining("\n")));
            return objectMapper.readValue(json, new TypeReference<List<StorageSystemRestRep>>() {
            });
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static String getVirtualPoolTemplate(
            final String path,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        try (final InputStream is = ViPRStubServiceImpl.class.getClassLoader().getResourceAsStream(path);
             final InputStreamReader isr = new InputStreamReader(is, UTF_8);
             final BufferedReader br = new BufferedReader(isr)
        ) {
            return propertiesResolver.resolve(br.lines().collect(Collectors.joining("\n")));
        }
    }
}
