package de.core.quickplan.enums;

/**
 * the languages that can get selected by an user<br>
 * also some specific locale 
 * 
 * @author Sebastian Kohler
 *
 */
public enum SelectableLanguage {
	/**
	 * German
	 */
	GERMAN("de","language.german"),
	/**
	 * English
	 */
	ENGLISH("en","language.english"),
	/**
	 * Spanish
	 */
	SPANISH("es","language.spanish"),
	/**
	 * French
	 */
	FRENCH("fr","language.french");
	/**
	 * the string needed as language-param for loading the required file
	 */
	private String fileEnding;
	/**
	 * the location to get the display-string from
	 */
	private String displayName;
	private SelectableLanguage(String file, String display)
	{
		fileEnding = file;
		displayName = display;
	}
	public String getFileEnding() {
		return fileEnding;
	}
	public String getDisplayName() {
		return displayName;
	}
}
