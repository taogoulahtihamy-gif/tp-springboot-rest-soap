package com.example.restclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath(
                "com.example.restclient.generated"
        );

        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(
            Jaxb2Marshaller marshaller,
            @Value("${soap.service.url}") String soapUrl) {

        WebServiceTemplate template = new WebServiceTemplate();

        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setDefaultUri(soapUrl);

        return template;
    }
}
