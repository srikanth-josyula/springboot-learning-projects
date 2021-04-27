package com.sample.utils;

import java.io.File;

public class ResourcesLocation {

	public String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		String resourcesPath = path + "/" + "src" + "/" + "main" + "/" + "resources";
		return resourcesPath;
	}
}
