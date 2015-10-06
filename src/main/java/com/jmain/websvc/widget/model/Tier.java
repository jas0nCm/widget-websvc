package com.jmain.websvc.widget.model;

public enum Tier {
	
	TIER_MINUS_ONE("TIER_MINUS_ONE", MaxAllowed.MAX, MaxAllowed.MAX, MaxAllowed.MAX)
	,TIER_ZERO("TIER_ZERO", MaxAllowed.MAX, MaxAllowed.MAX, MaxAllowed.MAX) 
	,TIER_ONE("TIER_ONE", 4, 3, 2)
	,TIER_TWO("TIER_TWO", 3, 2, 0)
	,TIER_THREE("TIER_THREE", 3, 1, 0)
	,TIER_FOUR("TIER_FOUR", 2, 0, 0)
	,TIER_FIVE("TIER_FIVE", 0, 0, 0);

	Tier(String value, int minorMaxAllowed, int majorMaxAllowed, int massiveMaxAllowed) {
		this.value = value;
		this.minorMaxAllowed = minorMaxAllowed;
		this.majorMaxAllowed = majorMaxAllowed;
		this.massiveMaxAllowed = massiveMaxAllowed;
	}
	
	private String value;
	private int minorMaxAllowed;
	private int majorMaxAllowed;
	private int massiveMaxAllowed;
	
	public String value() {
		return value;
	}
	
	public int minorMaxAllowed() {
		return minorMaxAllowed;
	}
	
	public int majorMaxAllowed() {
		return majorMaxAllowed;
	}
	
	public int massiveMaxAllowed() {
		return massiveMaxAllowed;
	}
	
	private static class MaxAllowed {
		public static final int MAX = Integer.MAX_VALUE;
	}
}
