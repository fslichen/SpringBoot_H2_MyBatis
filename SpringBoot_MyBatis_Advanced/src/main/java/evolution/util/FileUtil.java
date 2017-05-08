package evolution.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class FileUtil {
	public static Resource[] getResources(String basePackage) {
		File folder = new File(System.getProperty("user.dir") + "/src/main/java/" + basePackage.replace(".", "/"));
		File[] files = folder.listFiles();
		List<Resource> resources = new LinkedList<>();
		for (File file : files) {
			if (file.isFile()) {
				resources.add(new DefaultResourceLoader().getResource(basePackage.replace(".", "/") + "/" + file.getName()));
			}
		}
		Resource[] resourceArray = new Resource[resources.size()];
		for (int i = 0; i < resources.size(); i++) {
			resourceArray[i] = resources.get(i);
		}
		return resourceArray;
	}
}
