package com.sample.utils;

import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicBooleans;

public class AddCustomAIMLFiles {

	private static final boolean TRACE_MODE = false;
	static String botName = "super";

	public static void main(String[] args) {
		try {
			ResourcesLocation resource = new ResourcesLocation();
			String resourcesPath = resource.getResourcesPath();
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("super", resourcesPath);
			bot.writeAIMLFiles();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
