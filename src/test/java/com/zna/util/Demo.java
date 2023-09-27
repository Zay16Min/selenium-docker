package com.zna.util;

public class Demo {
	public static void main(String[] args) {
		System.setProperty("browser", "firefox");
		
		Config.intialize();
		System.out.println(Config.get("selenium.grid.hubHost"));
	}

}
