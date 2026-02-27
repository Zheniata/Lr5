package org.example.managers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.models.Organization;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XmlFileManager {
    private final XmlMapper xmlMapper;
    private final Path filePath;

    public XmlFileManager(String fileName) {
        this.xmlMapper = XmlMapper.builder()
                .defaultUseWrapper(false)
                .build();
        this.xmlMapper.registerModule(new JavaTimeModule());

        this.xmlMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        this.filePath = Path.of(fileName);
    }

    @JacksonXmlRootElement(localName = "organizations")
    public static class OrganizationsWrapper {

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "organization")
        public List<Organization> organizations;

        public OrganizationsWrapper() {}

        public OrganizationsWrapper(List<Organization> orgs) {
            this.organizations = new ArrayList<>(orgs);
        }
    }

    public List<Organization> readCollection() throws IOException{
        if (!filePath.toFile().exists()){
            return new ArrayList<>();
        }
        try (
                FileInputStream fis = new FileInputStream(filePath.toFile());
                InputStreamReader isr = new InputStreamReader(fis, java.nio.charset.StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr)
        )
        {
            OrganizationsWrapper wrapper = xmlMapper.readValue(reader, OrganizationsWrapper.class);
            return wrapper.organizations != null ? wrapper.organizations : new ArrayList<>();
        }
    }

    public void writeCollection(List<Organization> collection) throws IOException {
        OrganizationsWrapper wrapper = new OrganizationsWrapper(collection);

        try (
                FileOutputStream fos = new FileOutputStream(filePath.toFile());
                OutputStreamWriter osw = new OutputStreamWriter(fos, java.nio.charset.StandardCharsets.UTF_8)
        ) {
            xmlMapper.writeValue(osw, wrapper);
        }
    }
}
