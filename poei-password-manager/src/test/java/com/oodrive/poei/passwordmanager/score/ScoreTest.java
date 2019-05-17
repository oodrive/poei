package com.oodrive.poei.passwordmanager.score;

import org.junit.Assert;
import org.junit.Test;


public class ScoreTest {
	@Test
	public void from() {
		Assert.assertEquals(Score.LOW, Score.from(0f));
		Assert.assertEquals(Score.LOW, Score.from(10f));
		Assert.assertEquals(Score.LOW, Score.from(33f));
		Assert.assertEquals(Score.MEDIUM, Score.from(34f));
		Assert.assertEquals(Score.MEDIUM, Score.from(60f));
		Assert.assertEquals(Score.MEDIUM, Score.from(66f));
		Assert.assertEquals(Score.HIGH, Score.from(67f));
		Assert.assertEquals(Score.HIGH, Score.from(80f));
	}
}
