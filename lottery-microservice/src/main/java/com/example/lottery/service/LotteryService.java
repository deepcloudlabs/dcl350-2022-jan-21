package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class LotteryService {

	public List<List<Integer>> draw(int column){
	   return IntStream.range(0, column)
			           .mapToObj(i -> getLotteryNumbers())
			           .toList();
	}
	
	public List<Integer> getLotteryNumbers(){
		return ThreadLocalRandom.current()
				                .ints(1,60)
				                .distinct()
				                .limit(6)
				                .sorted()
				                .boxed()
				                .toList();
	}
}