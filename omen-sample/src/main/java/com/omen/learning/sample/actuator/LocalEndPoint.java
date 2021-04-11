package com.omen.learning.sample.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author suxing.zhang
 * @date 2021/4/11 14:16
 **/
@Component
@Endpoint(id = "localEndPoint")
public class LocalEndPoint {

    @ReadOperation
    public Map getDockerInfo() {
        return Collections.singletonMap("dockerInfo", "docker container is starting ....");
    }

    @WriteOperation
    public Map restartDockerInfo() {
        return Collections.singletonMap("dockerInfo", "docker container is restarting ....");
    }

}
