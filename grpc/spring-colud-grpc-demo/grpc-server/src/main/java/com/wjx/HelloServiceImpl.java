package com.wjx;

import com.google.protobuf.StringValue;
import com.wjx.grpc.api.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(StringValue request, StreamObserver<StringValue> responseObserver) {
        String value = request.getValue();
        responseObserver.onNext(StringValue.newBuilder().setValue("hello " + value).build());
        responseObserver.onCompleted();
    }
}