package com.emc.viprstub.service;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
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
            final ResourceLoader resourceLoader,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        this.objectMapper = configureMapper(objectMapper);
        this.virtualPools = new ArrayList<>(10);
        final Resource varraysResource = resourceLoader.getResource("classpath:data/varrays.json");
        virtualArrayList = getVirtualArrayList(varraysResource, objectMapper, propertiesResolver);
        final Resource storagePoolsResource = resourceLoader.getResource("classpath:data/storage-pools.json");
        storagePoolList = getStoragePoolsList(storagePoolsResource, objectMapper, propertiesResolver);
        final Resource storageSystemsResource = resourceLoader.getResource("classpath:data/storage-systems.json");
        storageSystemList = getStorageSystemList(storageSystemsResource, objectMapper, propertiesResolver);
        final Resource virtualPoolResource = resourceLoader.getResource("classpath:data/virtual-pool.json");
        virtualPoolTemplate = getVirtualPoolTemplate(virtualPoolResource, propertiesResolver);
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
            throw new RestClientException("Duplicate virtual pool name!");
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

    @SuppressWarnings({"AnonymousInnerClassMayBeStatic", "AnonymousInnerClass"})
    private static List<StorageSystemRestRep> getStorageSystemList(
            final Resource storageSystemsResource,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver) throws IOException {
        final byte[] content = Files.readAllBytes(storageSystemsResource.getFile().toPath());
        final String json = propertiesResolver.resolve(new String(content, UTF_8));
        return objectMapper.readValue(json, new TypeReference<List<StorageSystemRestRep>>() {
        });
    }

    @SuppressWarnings("AnonymousInnerClass")
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
        final String[] ignorableFieldNames = {"matchedStoragePools", "invalidMatchedStoragePools"};
        final FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter properties by name",
                        SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        objectMapper.setFilterProvider(filters);
        return objectMapper;
    }

    @SuppressWarnings("AnonymousInnerClass")
    private static List<VirtualArrayRestRep> getVirtualArrayList(
            final Resource varraysResource,
            final ObjectMapper objectMapper,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        final byte[] content = Files.readAllBytes(varraysResource.getFile().toPath());
        final String json = propertiesResolver.resolve(new String(content, UTF_8));
        return objectMapper.readValue(json, new TypeReference<List<VirtualArrayRestRep>>() {
        });
    }

    private static String getVirtualPoolTemplate(
            final Resource varraysResource,
            final PropertiesResolver propertiesResolver)
            throws IOException {
        final byte[] content = Files.readAllBytes(varraysResource.getFile().toPath());
        return propertiesResolver.resolve(new String(content, UTF_8));
    }
}
