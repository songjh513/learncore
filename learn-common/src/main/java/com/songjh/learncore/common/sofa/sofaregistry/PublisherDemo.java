package com.songjh.learncore.common.sofa.sofaregistry;

import com.alipay.sofa.registry.client.api.RegistryClientConfig;
import com.alipay.sofa.registry.client.api.registration.PublisherRegistration;
import com.alipay.sofa.registry.client.provider.DefaultRegistryClient;
import com.alipay.sofa.registry.client.provider.DefaultRegistryClientConfigBuilder;

/**
 * Created  by songjh on 2019-07-13 08:09.
 */
public class PublisherDemo {

    public static void main(String[] args){
        // 构建客户端实例
        RegistryClientConfig config = DefaultRegistryClientConfigBuilder.start()
                                    .setRegistryEndpoint("127.0.0.1")
                                    .setRegistryEndpointPort(9603)
                                    .build();
        DefaultRegistryClient registryClient = new DefaultRegistryClient(config);
        registryClient.init();

        // 构造发布者注册表
        String dataId = "com.alipay.test.demo.service:1.0@DEFAULT";
        PublisherRegistration registration = new PublisherRegistration(dataId);
        registration.setGroup("TEST_GROUP");
        registration.setAppName("TEST_APP");

       // 将注册表注册进客户端并发布数据
        registryClient.register(registration, "10.10.1.1:12200?xx=yy");
    }
}
