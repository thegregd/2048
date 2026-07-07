package com.greg._2048;

import com.greg._2048.service.Move;
import com.greg._2048.service.MoveUp;
import org.junit.jupiter.api.BeforeEach;

public class MoveTest {
	private Move<int[][]> move;

	@BeforeEach
	void setUp() {
		move = new MoveUp();
	}
	
}
