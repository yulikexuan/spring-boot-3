//: sfg6lab.config.YamlPropertySourceFactory.java


package sfg6lab.config;


import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.Properties;


class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public @NonNull PropertySource<?> createPropertySource(
            String name, EncodedResource encodedResource) throws IOException {

        Resource res = encodedResource.getResource();
        String resName = res.getFilename();

        if (resName == null) {
            resName = "YAL_RES";
        }

        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(res);

        Properties properties = factory.getObject();
        if (properties == null) {
            properties = new Properties();
        }

        return new PropertiesPropertySource(resName, properties);
    }

}///:~