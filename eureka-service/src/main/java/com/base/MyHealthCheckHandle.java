package com.base;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheckHandle implements HealthCheckHandler {

    @Autowired
    private MyHealthIndicator indicator;

    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status status = indicator.health().getStatus();
        if (status.equals(Status.UP)) {
            System.out.println("数据库正常连接");
            return InstanceInfo.InstanceStatus.UP;
        } else {
            System.out.println("数据库无法连接");
            return InstanceInfo.InstanceStatus.DOWN;
        }
    }
}
