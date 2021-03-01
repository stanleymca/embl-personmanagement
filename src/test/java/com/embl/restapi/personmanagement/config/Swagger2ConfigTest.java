package com.embl.restapi.personmanagement.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import springfox.documentation.spring.web.plugins.Docket;

@RunWith(MockitoJUnitRunner.class)
public class Swagger2ConfigTest {
    @Test
    public void apiTest() {
        Swagger2Config swagger2Config=new Swagger2Config();
        Docket docket=swagger2Config.api();
        assertThat(docket).isNotNull();
    }
}
