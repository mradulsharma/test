package com.madiv.generic.util;

public class NumberUtil {
	public static int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
}
