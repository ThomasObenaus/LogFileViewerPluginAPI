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

/**
 * @author Thomas Obenaus
 * @source IPluginApiVersion.java
 * @date Apr 9, 2015
 */
public interface IPluginApiVersion
{
	/**
	 * Returns the major version of this api (NOT backward-compatible, e.g. old functions removed).
	 * @return
	 */
	public int getMajorVersion( );

	/**
	 * Returns the minor version of this api (backward-compatible, e.g. new functions added).
	 * @return
	 */
	public int getMinorVersion( );

	/**
	 * Returns the bugfix version of this api (backward-compatible, no functions modified).
	 * @return
	 */
	public int getBugfixVersion( );

	/**
	 * Returns false if the major versions differ or if the minor version of the plugin is greater than this {@link IPluginApiVersion}.
	 * Otherwise true is returned.
	 * @param versionOfPlugin
	 * @return
	 */
	public boolean isCompatible( IPluginApiVersion versionOfPlugin );
}
