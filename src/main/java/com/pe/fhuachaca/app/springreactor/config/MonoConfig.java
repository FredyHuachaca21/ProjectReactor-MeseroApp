package com.pe.fhuachaca.app.springreactor.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MonoConfig implements InitializingBean {

    @Autowired
    @Lazy //No lo instancia si no le pide
    private MappingMongoConverter mappingMongoConverter;

    @Override
    public void afterPropertiesSet() throws Exception {
            mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
