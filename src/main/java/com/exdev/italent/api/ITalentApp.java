package com.exdev.italent.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.persistence.PreUpdate;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@ApplicationPath("/api")
public class ITalentApp extends Application {

	@Context
	public static ServletContext context;

	public static final Properties CONFIG = new Properties();
	public static final Logger LOGGER = Logger.getLogger("Italentapp Logger");

	private String propFileName = "/WEB-INF/config.properties";

	@Override
	public Set<Object> getSingletons() {
		setLocalAndTimeZone();
		loadConfig();
		LOGGER.info(CONFIG.getProperty("app_name"));
		return super.getSingletons();
	}

	private void loadConfig() {
		LOGGER.info("Loading Configurations: " + propFileName);
		try {
			InputStream in = context.getResourceAsStream(propFileName);
			if (in != null) {
				CONFIG.load(in);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
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
