
package com.voucher.configuration;

import java.io.File;

/**
 * 
 * @author Ravindra kumar
 * @email rv.kumar1401@gmail.com
 *
 */
public interface ConfigConstants {

	
	public static final String PATH_CATALINA_HOME			= "catalina.home";
	public static final String PATH_CATALINA_BASE			= "catalina.base";
	public static final String PROPERTY_FILENAME			= "voucher";
	
	public static final String PROPERTIES_EXT				= ".properties";
	public static final String FILE_SYS_RESOURCE			= File.separator + PROPERTY_FILENAME + PROPERTIES_EXT;
	public static final String PROPERTY_CLASSPATH			= "classpath:" + PROPERTY_FILENAME;
	public static final String FILE_PFX						= "file:";
	
	public static final String DB_CONF_DRIVER				= "mysql.db.driver";
	public static final String DB_CONF_URL					= "mysql.db.url";
	public static final String DB_CONF_UNAME				= "mysql.db.uname";
	public static final String DB_CONF_PWORD				= "mysql.db.pword";
	public static final String CRON_JOB_SCHEDULER			= "batch.job.schedular";
	
	public static final String WEEKDAY_SLOTS="weekdays.inspection.slot";
	public static final String SATDAY_SLOTS="saturday.inspection.slot";
	public static final String DIVIDENT_TIME="divident.time";
	
	/** The Constant EMPTY_STRING. */
	public static final String EMPTY_STRING = "";

	/** The Constant EMPTY_ARRAY. */
	public static final String[] EMPTY_ARRAY = new String[0];

	/** The Constant SPACE. */
	public static final String SPACE = " ";

	/** The Constant SLASH. */
	public static final String SLASH = "/";

	/** The Constant COMMA. */
	public static final String COMMA = ",";

	/** The Constant DASH. */
	public static final String DASH = "-";

	public static final String AMPERSAND = "#";

	/** The Constant Y. */
	public static final String Y = "Y";

	/** The Constant N. */
	public static final String N = "N";

	// [START] - IDM USER STATUS

	/** The Constant USER_FIRST_TIME. */
	public static final String USER_FIRST_TIME = "F";

	/** The Constant USER_ACTIVE. */
	public static final String USER_ACTIVE = "A";

	/** The Constant USER_PENDING_ACTIVATION. */
	public static final String USER_PENDING_ACTIVATION = "P";

	/** The Constant USER_INACTIVE. */
	public static final String USER_INACTIVE = "I";

	// [END] - IDM USER STATUS

	/** The Constant ZERO. */
	public static final Integer ZERO = 0;

	/** The Constant DAY. */
	public static final String DAY = "DAY";

	/** The Constant MONTH. */
	public static final String MONTH = "MONTH";

	/** The Constant YEAR. */
	public static final String YEAR = "YEAR";

	/** The Constant SIMPLE_DATE_FORMAT. */
	public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

	/** The Constant SIMPLE_TIMESTAMP_FORMAT. */
	public static final String SIMPLE_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

	/** The Constant DT_TIME_A. */
	public static final String DT_TIME_A = "HH:mm a";

	/** The Constant DT_TIME_S. */
	public static final String DT_TIME_S = "HH:mm:ss";

	/** The Constant DT_TIME_MS. */
	public static final String DT_TIME_MS = "HH:mm:ss.s";

	/** The Constant DT_YYYY_MM_DD_DASH. */
	public static final String DT_YYYY_MM_DD_DASH = "yyyy-MM-dd";

	/** The Constant DT_YYYY_MM_DD_DASH_TIME_A. */
	public static final String DT_YYYY_MM_DD_DASH_TIME_A = "yyyy-MM-dd HH:mm a";

	/** The Constant DT_YYYY_MM_DD_DASH_TIME_S. */
	public static final String DT_YYYY_MM_DD_DASH_TIME_S = "yyyy-MM-dd HH:mm:ss";

	/** The Constant DT_YYYY_MM_DD_DASH_TIME_MS. */
	public static final String DT_YYYY_MM_DD_DASH_TIME_MS = "yyyy-MM-dd HH:mm:ss.s";

	/** The Constant DT_YYYY_MM_DD_SLASH. */
	public static final String DT_YYYY_MM_DD_SLASH = "yyyy/MM/dd";

	/** The Constant DT_YYYY_MM_DD_SLASH_TIME_A. */
	public static final String DT_YYYY_MM_DD_SLASH_TIME_A = "yyyy/MM/dd HH:mm a";

	/** The Constant DT_YYYY_MM_DD_SLASH_TIME_S. */
	public static final String DT_YYYY_MM_DD_SLASH_TIME_S = "yyyy/MM/dd HH:mm:ss";

	/** The Constant DT_YYYY_MM_DD_SLASH_TIME_MS. */
	public static final String DT_YYYY_MM_DD_SLASH_TIME_MS = "yyyy/MM/dd HH:mm:ss.s";

	/** The Constant DT_DD_MM_YYYY_DASH. */
	public static final String DT_DD_MM_YYYY_DASH = "dd-MM-yyyy";

	/** The Constant DT_DD_MMM_YYYY_DASH. */
	public static final String DT_DD_MMM_YYYY_DASH = "dd-MMM-yyyy";

	/** The Constant DT_DD_MM_YYYY_DASH_TIME_A. */
	public static final String DT_DD_MM_YYYY_DASH_TIME_A = "dd-MM-yyyy hh:mm a";

	/** The Constant DT_DD_MM_YYYY_DASH_TIME_S. */
	public static final String DT_DD_MM_YYYY_DASH_TIME_S = "dd-MM-yyyy HH:mm:ss";

	/** The Constant DT_DD_MM_YYYY_DASH_TIME_MS. */
	public static final String DT_DD_MM_YYYY_DASH_TIME_MS = "dd-MM-yyyy HH:mm:ss.s";

	/** The Constant DT_DD_MM_YYYY_SLASH. */
	public static final String DT_DD_MM_YYYY_SLASH = "dd/MM/yyyy";

	/** The Constant DT_DD_MM_YYYY_SLASH_TIME_A. */
	public static final String DT_DD_MM_YYYY_SLASH_TIME_A = "dd/MM/yyyy hh:mm a";

	/** The Constant DT_DD_MM_YYYY_SLASH_TIME_S. */
	public static final String DT_DD_MM_YYYY_SLASH_TIME_S = "dd/MM/yyyy HH:mm:ss";

	/** The Constant DT_DD_MM_YYYY_SLASH_TIME_MS. */
	public static final String DT_DD_MM_YYYY_SLASH_TIME_MS = "dd/MM/yyyy HH:mm:ss.s";

	/** The Constant STATUS_ACTIVE. */
	public static final String STATUS_ACTIVE = "A";

	/** The Constant STATUS_INACTIVE. */
	public static final String STATUS_INACTIVE = "I";

	/** The Constant HEADER_MESSAGE_ID. */
	public static final String HEADER_MESSAGE_ID = "X-Message-Id";

	/** The Constant HEADER_AUTHORIZATION. */
	public static final String HEADER_AUTHORIZATION = "Authorization";

	/** The Constant NEW_LINE. */
	public static final String NEW_LINE = System.lineSeparator();

	/** The Constant LOG_SEPARATOR. */
	public static final String LOG_SEPARATOR = "---------------------------------------------------------------------------------------";

	/** The Constant LOG_IDM_EXCEPTION. */
	public static final String LOG_IDM_EXCEPTION = "IdmException: {}";

	/** The Constant LOG_EXCEPTION. */
	public static final String LOG_EXCEPTION = "Exception: {}";

	/** The Constant SYNC_INITIATE. */
	public static final int SYNC_INITIATE = 0;

	/** The Constant SYNC_INPROGRESS. */
	public static final int SYNC_INPROGRESS = 1;

	/** The Constant SYNC_COMPLETED. */
	public static final int SYNC_COMPLETED = 2;

	/** The Constant MENU_LEVEL. */
	public static final String MENU_LEVEL = "menuLevel";

	/** The Constant MENU_CODE. */
	public static final String MENU_CODE = "menuCode";

	/** The Constant ROLE_CODE. */
	public static final String ROLE_CODE = "roleCode";

	/** The Constant EMBED_MENU. */
	public static final String EMBED_MENU = "embededMenu";

	/** The Constant EMBED_ROLE. */
	public static final String EMBED_ROLE = "embededRole";

	/** The Constant EMPTY_BRACE. */
	public static final String EMPTY_BRACE = " {}";

	/** The Constant PERMISSION_CODE. */
	public static final String PERMISSION_CODE = "txnNo";

	/** The Constant MAIL. */
	public static final String MAIL = "mail";

	/** The Constant MAIL_HEADER_IMG. */
	public static final String MAIL_HEADER_IMG = "cid:MAIL_HEADER";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "SUCCESS";

	/** The Constant FAILED. */
	public static final String FAILED = "FAILED";

	/** The Constant LOGIN. */
	public static final String LOGIN = "LOGIN";

	/** The Constant USER_PROFILE. */
	public static final String USER_PROFILE = "USER PROFILE";

	/** The Constant AMOUNT_FORMAT. **/
	public static final String AMOUNT_FORMAT = "#,###.00";
	
	/** The Constant CONFIG_CODE. */
	public static final String CONFIG_CODE = "configCode";
	
	public static final String GROUP_CODE = "groupCode";
	
	
	
}
