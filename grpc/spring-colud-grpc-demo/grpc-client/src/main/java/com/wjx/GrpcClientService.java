package com.wjx;

import com.google.protobuf.StringValue;
import com.wjx.grpc.api.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientService {

    @GrpcClient("grpc_server")
    HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public void hello() {
        StringValue s = helloServiceBlockingStub.sayHello(StringValue.newBuilder().setValue("javaboy").build());
        System.out.println("s = " + s.getValue());
    }
}