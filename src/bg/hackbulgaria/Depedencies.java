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

	public static Boolean isInstalled(String js_package) {

		Path path = FileSystems.getDefault().getPath("installed_modules", js_package);

		if (Files.exists(path)) {
			return true;
		}

		return false;
	}

	public static Boolean allAreInstalled(JSONArray getDep) {
		if (getDep.length() > 0) {
			for (int i = 0; i < getDep.length(); i++) {
				if (!isInstalled(getDep.getString(i))) {
					return false;
				}
			}
		} else {
			return true;
		}
		return true;

	}

	public static void installDependency(JSONArray js_local_pack, JSONObject allPackages) {

		for (int i = 0; i < js_local_pack.length(); i++) {
			JSONArray getDep = new JSONArray(allPackages.getJSONArray(js_local_pack.getString(i)).toString());

			if (!isInstalled(js_local_pack.getString(i))) {
				if ((getDep.length() == 0)) {

					File file = new File("installed_modules//" + js_local_pack.getString(i));
					if (!file.exists()) {
						if (file.mkdir()) {
							System.out.println(js_local_pack.getString(i) + " is instaled!");
						} else {
							System.out.println("Failed to install dependency!");
						}
					}

				}
				for (int j = 0; j < getDep.length(); j++) {
					if (allAreInstalled(getDep) && getDep.length() != 0) {
						File file = new File("installed_modules//" + js_local_pack.getString(i));
						if (!file.exists()) {
							if (file.mkdir()) {
								System.out.println(js_local_pack.getString(i) + " is instaled!");
							} else {
								System.out.println("Failed to install dependency!");
							}
						}

					}

					if ((getDep.length() != 0) && !isInstalled(getDep.getString(j))) {
						JSONArray old_js_lc = js_local_pack;
						js_local_pack = getDep;// new
												// JSONArray(allPackages.getJSONArray(getDep.getString(j)).toString());

						installDependency(js_local_pack, allPackages);

					}
				}
			}
		}
	}

	public static void main(String[] args) {

		String jsonAll = readFile("all_packages.json");
		String jsonLocal = readFile("dependencies.json");

		JSONObject allPackages = new JSONObject(jsonAll);
		JSONObject jobjLocal = new JSONObject(jsonLocal);

		JSONArray jvalueLocal = new JSONArray(jobjLocal.getJSONArray("dependencies").toString());
		JSONArray jvalueLocal_Old = jvalueLocal;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("install")) {

				for (int i1 = 0; i1 < jvalueLocal_Old.length(); i1++) {
					installDependency(jvalueLocal, allPackages);

					if (isInstalled(jvalueLocal_Old.getString(i1))) {
						jvalueLocal_Old.remove(i1);
						installDependency(jvalueLocal_Old, allPackages);
					} else {
						installDependency(jvalueLocal_Old, allPackages);
					}
				}
			}
		}

	}

}
