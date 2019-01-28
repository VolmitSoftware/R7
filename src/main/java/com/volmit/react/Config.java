package com.volmit.react;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public class Config
{
	// Reactions
	@Clip(min = 0D, max = 1D)
	@Comment("How... React should react be? For example 0.5 means react should be balanced in executing actions versus letting things happen a little bit longer before react does anything.")
	@Key("react.reactions.reactivity")
	public static double REACT_REACTIONS_REACTIVITY = 0.3;

	// Monitoring
	@Clip(min = 0D, max = 1D)
	@Comment("Defines the quality of react's sample data. Where 0 is high efficiency, but low quality, and 1 is maximum quality with extra cpu usage.")
	@Key("react.monitoring.monitoring-quality")
	public static double REACT_MONITORING_QUALITY = 0.5;

	// Databases
	@Comment("Use a database for storing playerdata files instead of flat file. This can be useful for bungeecord networks since react will remember your settings when you switch servers.")
	@Key("react.storage.database.enabled")
	public static boolean REACT_STORAGE_DATABASE_ENABLED = false;

	@Comment("The address to the database")
	@Key("react.storage.database.address")
	public static String REACT_STORAGE_DATABASE_ADDRESS = "localhost";

	@Clip(min = 0D, max = 65535)
	@Comment("The port to connect to the database")
	@Key("react.storage.database.port")
	public static int REACT_STORAGE_DATABASE_PORT = 3306;

	@Comment("The database user's password")
	@Key("react.storage.database.password")
	public static String REACT_STORAGE_DATABASE_PASSWORD = "securepasswordorsomething1234";

	@Comment("The database username")
	@Key("react.storage.database.username")
	public static String REACT_STORAGE_DATABASE_USERNAME = "reactdb";

	@Comment("The database name")
	@Key("react.storage.database.database")
	public static String REACT_STORAGE_DATABASE_DATABASE = "database_name";

	@Comment("You can change the table prefix for react.")
	@Key("react.storage.database.table-prefix")
	public static String REACT_STORAGE_DATABASE_PREFIX = "react_";

	@Retention(RUNTIME)
	@Target(FIELD)
	public @interface Comment
	{
		String value();
	}

	@Retention(RUNTIME)
	@Target(FIELD)
	public @interface Key
	{
		String value();
	}

	@Retention(RUNTIME)
	@Target(FIELD)
	public @interface Clip
	{
		public double min() default Double.MIN_VALUE;

		public double max() default Double.MAX_VALUE;
	}

}
