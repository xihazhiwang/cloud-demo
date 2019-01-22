package com.base.controller;

import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Service;
import java.util.List;

@RestController
public class InvokerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public String router() {
        List<ServiceInstance> ins = getServiceInstances();
        for (ServiceInstance service : ins) {
            EurekaDiscoveryClient.EurekaServiceInstance esi = (EurekaDiscoveryClient.EurekaServiceInstance) service;
            InstanceInfo info = esi.getInstanceInfo();
            System.out.println(info.getAppName() + "---" + info.getInstanceId() + "---" + info.getStatus());
        }
        return "";

    }

    private List<ServiceInstance> getServiceInstances() {
        List<String> ids = discoveryClient.getServices();
        List<ServiceInstance> result = Lists.newArrayList();

        for (String id : ids) {
            List<ServiceInstance> ins = discoveryClient.getInstances(id);
            result.addAll(ins);
        }
        return result;
    }


}
