package com.emc.viprstub;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.varray.VirtualArrayList;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class ViPRStubServiceImpl implements ViPRStubService {
    private final VirtualArrayList virtualArrayList;

    @Autowired
    public ViPRStubServiceImpl(
            final ResourceLoader resourceLoader,
            final ObjectMapper objectMapper)
            throws IOException {
        final ObjectMapper json = configureMapper(objectMapper);
        final Resource varraysResource = resourceLoader.getResource("classpath:data/varrays.json");
        virtualArrayList = getVirtualArrayList(varraysResource, json);
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

    private static ObjectMapper configureMapper(final ObjectMapper objectMapper) {
        final AnnotationIntrospector aiJaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector aiJackson = new JacksonAnnotationIntrospector();
        objectMapper.setAnnotationIntrospector(AnnotationIntrospector.pair(aiJaxb, aiJackson));
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper;
    }

    private static VirtualArrayList getVirtualArrayList(
            final Resource varraysResource,
            final ObjectMapper json)
            throws IOException {
        return json.readValue(varraysResource.getFile(), VirtualArrayList.class);
    }
}
