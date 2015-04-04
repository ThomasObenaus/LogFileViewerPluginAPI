/*
 *  Copyright (C) 2014, Thomas Obenaus. All rights reserved.
 *  Licensed under the New BSD License (3-clause lic)
 *  See attached license-file.
 *
 *	Author: 	Thomas Obenaus
 *	EMail:		obenaus.thomas@gmail.com
 *  Project:    LogFileViewer
 */

package thobe.logfileviewer.plugin.api;

import java.util.prefs.Preferences;

/**
 * @author Thomas Obenaus
 * @source IPluginPreferences.java
 * @date Apr 2, 2015
 */
public interface IPluginPreferences
{
	/**
	 * This method will be called at startup. Implement this method loading your plugins preferences. <br>
	 * e.g.: <code><br> 
	 * // retrieve the node called 'color-settings'<br>
	 * Preferences root = pluginPrefRoot.node( "color-settings" );<br>
	 * <br>
	 * // retrieve the property 'text-color'<br>
	 * String txtColor = root.get( "text-color", "black" );<br> 
	 * // retrieve the property 'background-color'<br>
	 * String bgColor = root.get( "background-color", "white" );<br>  
	 * </code>   
	 * @param pluginPrefRoot
	 */
	public void load( Preferences pluginPrefRoot );

	/**
	 * This method will be called on shutdown. Implement this method saving your plugins preferences. <br>
	 * e.g.: <code><br> 
	 * // retrieve the node called 'color-settings'<br>
	 * Preferences root = pluginPrefRoot.node( "color-settings" );<br>
	 * <br>
	 * // set the property 'text-color'<br>
	 * String txtColor = root.put( "text-color", "green" );<br> 
	 * // set the property 'background-color'<br>
	 * String bgColor = root.put( "background-color", "light-gray" );<br>  
	 * </code>   
	 * @param pluginPrefRoot
	 */
	public void save( Preferences pluginPrefRoot );
}
