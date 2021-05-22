package dk.xpreuss.xperimentering;

import io.reactivex.rxjava3.core.*;

public class Main2 {
	public static void main(String[] args) {

		Flowable.just("Hello world").subscribe(System.out::println);
	}
}
