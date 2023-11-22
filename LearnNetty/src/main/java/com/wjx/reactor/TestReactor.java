package com.wjx.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Author WangJX
 * @Date 2023/9/5 18:58
 * @Description
 */
public class TestReactor {

    public static void main(String[] args) throws IOException {
//        //just()：创建Flux序列，并声明数据流，
//        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);//整形
////subscribe()：订阅Flux序列，只有进行订阅后才回触发数据流，不订阅就什么都不会发生
//        integerFlux.subscribe(System.out::println);
//        Flux<String> stringFlux = Flux.just("hello", "world");//字符串
//        stringFlux.subscribe(System.out::println);
////fromArray(),fromIterable()和fromStream()：可以从一个数组、Iterable 对象或Stream 对象中创建Flux序列
//
//        Integer[] array = {1,2,3,4};
//        Flux.fromArray(array).subscribe(System.out::println);
//        List<Integer> integers = Arrays.asList(array);
//        Flux.fromIterable(integers).subscribe(System.out::println);
//        Stream<Integer> stream = integers.stream();
//        Flux.fromStream(stream).subscribe(System.out::println);

//
//        Flux.just("Ben", "Michael", "Mark1").subscribe(new Subscriber<String>() {
//            public void onSubscribe(Subscription s) {
//                s.request(3);
//            }
//            public void onNext(String s) {
//                System.out.println("Hello " + s + "!");
//            }
//            public void onError(Throwable t) {
//            }
//            public void onComplete() {
//                System.out.println("Completed");
//            }
//        });
//
//        Flux.just("Ben", "Michael", "Mark2")
//                .doOnNext(s -> System.out.println("Hello " + s + "!"))
//                .doOnComplete(() -> System.out.println("Completed"))
//                .subscribe();
        //如果仅对前 N 个元素感兴趣， take() 运算符将限制发射项目的数量。
//        Flux.just("Ben", "Michael", "Mark") //
//                .doOnNext(s -> System.out.println("Hello " + s + "!"))
//                .doOnComplete(() -> System.out.println("Completed"))
//                .take(2)
//                .subscribe();

//
//        Flux.just("Ben", "Michael", "Mark3").subscribe(new Subscriber<String>() {
//            public void onSubscribe(Subscription s) {
//                s.request(3);
//            }
//            public void onNext(String s) {
//                System.out.println("Hello " + s + "!");
//            }
//            public void onError(Throwable e) {
//                System.out.println("onError: " + e);
//            }
//            public void onComplete() {
//                System.out.println("Completed");
//            }
//        });


//
//        String last = Flux.just("Ben", "Michael", "Mark").last().block();
//        System.out.println(last);
//
//        List<String> list = Flux.just("Ben", "Michael", "Mark").collectList().block();
//        System.out.println(list);
//
//        Flux<Integer> ints = Flux.range(1, 4);
//        Flux seq1 = Flux.just("bole1", "bole2", "bole3");
//        List iterable = Arrays.asList("bole_01", "bole_02", "bole_03");
//        Flux seq2 = Flux.fromIterable(iterable);
//        seq2.subscribe(System.out::println);

//
//        Flux.just("Hello", "World").subscribe(System.out::println);
//        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
//        Flux.empty().subscribe(System.out::println);
//        Flux.range(1, 10).subscribe(System.out::println);
//        Flux.interval(Duration.of(10,
//                ChronoUnit.NANOS)).subscribe(System.out::println);
//        Flux.intervalMillis(1000).subscirbe(System.out::println);


        Flux.generate(sink -> {
            sink.next("Hello");
//            for(int i = 0; i < 10; i ++)
//                sink.next(i);
            sink.complete();
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable.getMessage()");
            }

            @Override
            public void onComplete() {

            }
        });
//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            if( list.size() ==10 )
//                sink.complete();
//            return list;
//        }).subscribe(System.out::println);

//
//        Flux.create(sink -> {
//            for(int i = 0; i < 10; i ++)
//                sink.next(i);
//            sink.complete();
//        }).subscribe(System.out::println);
//        System.in.read();


        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

        Flux.just(1, 2).concatWith(Mono.error(new
                IllegalStateException())).subscribe(System.out::println, System.err::println);
        Flux.just(1, 2).concatWith(Mono.error(new
                IllegalStateException())).onErrorReturn(0).subscribe(System.out::println);
    }
}
