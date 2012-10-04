package ultraextreme.util;

import java.util.HashMap;
import java.util.Map;

public class Resources {

	private Map<ResourceName, String> resourceMap = new HashMap<ResourceName, String>();

	public enum ResourceName {
		START_GAME, SCORE, LIVES;
	}

	private static Resources instance;

	private Resources() {
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}

	public String getResource(ResourceName resName) {
		return resourceMap.get(resName);
	}

	public void setResource(ResourceName resName, String resource) {
		resourceMap.put(resName, resource);
	}
}