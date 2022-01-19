package com.spring.grpc.grpcclient.observer;

import io.grpc.stub.StreamObserver;

public class NoopStreamObserver<T> implements StreamObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
