package Utils.Helpers;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Abstractions.Models.LanguageModel;

public class LanguageLoader {

	static Document document;
	static Element racine;

	public LanguageLoader() {
	}

	public LanguageModel load(File f) {

		SAXBuilder sxb = new SAXBuilder();
		try {

			document = sxb.build(f);

		} catch (Exception e) {
			System.out.println("FILE ERROR : " + e);
			return null;
		}

		racine = document.getRootElement();

		return new LanguageModel(racine);

	}

	public LanguageModel load(Reader r) {

		SAXBuilder sxb = new SAXBuilder();
		try {

			document = sxb.build(r);

		} catch (Exception e) {
			System.out.println("FILE ERROR : " + e);
			return null;
		}

		racine = document.getRootElement();

		return new LanguageModel(racine);

	}

	public LanguageModel load(String path) {
		LanguageModel model = new LanguageModel();
		SAXBuilder sxb = new SAXBuilder();
		try {
			if (!path.contains(".jar")) {
				File directory = new File(path);
				for (File file : directory.listFiles()) {
					document = sxb.build(file);

					racine = document.getRootElement();

					model.addElem(racine);
				}
				return model;
			}

			URL urlDescription = null;
			JarFile jar = new JarFile(URLDecoder.decode(
					path.substring(9, path.indexOf("!")), "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.contains("Ressources/Languages")) {

					String p = path + name;
					urlDescription = new URL(p);

					InputStream input = null;
					input = urlDescription.openStream();

					Reader reader = new InputStreamReader(input, "UTF8");

					document = sxb.build(reader);
					racine = document.getRootElement();

					model.addElem(racine);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return model;
	}
}
