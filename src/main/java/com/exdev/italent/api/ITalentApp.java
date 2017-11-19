package com.exdev.italent.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/api")
public class ITalentApp extends Application {

	@Context
	public static ServletContext context;

	public static final Properties CONFIG = new Properties();
	public static final Logger LOGGER = Logger.getLogger("Italentapp Logger");

	public static String CONFIG_FILE_PATH;

	@Override
	public Set<Object> getSingletons() {
		setLocalAndTimeZone();
		CONFIG_FILE_PATH = context.getInitParameter("CONFIG_FILE");
		loadConfig();
		LOGGER.info(CONFIG.getProperty("app_name"));
		return super.getSingletons();
	}

	public static void loadConfig() {
		LOGGER.info("Loading Configurations: " + CONFIG_FILE_PATH);
		try {
//			InputStream in = context.getResourceAsStream(CONFIG_FILE_PATH);
			FileReader in = new FileReader(CONFIG_FILE_PATH);
			if (in != null) {
				CONFIG.load(in);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG_FILE_PATH + "' not found in the classpath");
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setLocalAndTimeZone() {
		Locale local = new Locale("ar", "SA");
		TimeZone ts = TimeZone.getTimeZone("Asia/Riyadh");
		LOGGER.info("Set  Local and Timezone: " + local+", Time Zone: " + ts);
		Locale.setDefault(local);
		TimeZone.setDefault(ts);
	}
}
