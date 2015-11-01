package bg.hackbulgaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

public class Depedencies {

	public static String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void installDependency(String js_package) {

		File file = new File("installed_modules//" + js_package);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println(js_package + " is instaled!");
			} else {
				System.out.println("Failed to install dependency!");
			}
		}
	}

	public static Boolean isInstalled(String js_package) {

		Path path = FileSystems.getDefault().getPath("installed_modules", js_package);

		if (Files.exists(path)) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {

		// System.out.println( readFile("all_packages.json"));
		String jsonAll = readFile("all_packages.json");
		String jsonLocal = readFile("dependencies.json");

		JSONObject jobjLocal = new JSONObject(jsonLocal);
		JSONArray jarr = new JSONArray(jobjLocal.getJSONArray("dependencies").toString());

		JSONObject allJs = new JSONObject(jsonAll);
		JSONArray getDep = new JSONArray(allJs.getJSONArray( jarr.getString(0) ));

		System.out.println(getDep);
		for (int i = 0; i < jarr.length(); i++) {
			if (isInstalled(jarr.getString(i))) {

			}
		}

	}

}
