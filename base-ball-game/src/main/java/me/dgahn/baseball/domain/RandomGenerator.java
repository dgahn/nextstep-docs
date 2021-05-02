package me.dgahn.baseball.domain;

public interface RandomGenerator<T> {

	void generate();

	T get();

	void clear();

}
