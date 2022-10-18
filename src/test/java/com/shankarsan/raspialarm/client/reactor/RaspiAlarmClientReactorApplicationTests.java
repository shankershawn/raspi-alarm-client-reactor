package com.shankarsan.raspialarm.client.reactor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

@SpringBootTest
class RaspiAlarmClientReactorApplicationTests {

	@Test
	public void test1() {
		Flux<String> myFlux = Flux.just("hello", "nice", "to", "see", "you").log();
		myFlux.subscribe(System.out::println);
	}
	
	private void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Flux<String> myFlux = Flux.just("hello", "nice", "to", "see", "you").doOnNext(e -> this.sleep(1000)).log().publish().autoConnect();
		myFlux.subscribe(System.out::println);
		
	}
	
	@Test
	public void test3() {
		ConnectableFlux<Object> myConnectableFlux = Flux.create(e -> {
			int i = 20;
			while(i-- > 0) {
				System.out.println(i);
				e.next(String.valueOf(i));
				sleep(100);
			}
		}).publish();
		myConnectableFlux.connect(System.out::println);
		System.out.println("Connected to flux!");
		
	}

}
