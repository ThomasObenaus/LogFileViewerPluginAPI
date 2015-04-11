/*
 *  Copyright (C) 2014, Thomas Obenaus. All rights reserved.
 *  Licensed under the New BSD License (3-clause lic)
 *  See attached license-file.
 *
 *	Author: 	Thomas Obenaus
 *	EMail:		obenaus.thomas@gmail.com
 *  Project:    LogFileViewer
 */

package thobe.logfileviewer.plugin;

import thobe.logfileviewer.plugin.api.IPluginApiVersion;

/**
 * @author Thomas Obenaus
 * @source PluginApiVersion.java
 * @date Mar 7, 2015
 */
public class PluginApiVersion implements IPluginApiVersion
{
	/**
	 * Increase for incompatible changes
	 */
	private static final int MAJOR_VERSION = 3;

	/**
	 * Increase for compatible changes
	 */
	private static final int MINOR_VERSION = 2;

	/**
	 * Increase for bugfixes
	 */
	private static final int BUGFIX_VERSION = 1;

	private int					majorVersion;
	private int					minorVersion;
	private int					bugfixVersion;

	public PluginApiVersion( )
	{
		this.majorVersion = MAJOR_VERSION;
		this.minorVersion = MINOR_VERSION;
		this.bugfixVersion = BUGFIX_VERSION;
	}

	public int getMajorVersion( )
	{
		return majorVersion;
	}

	public int getMinorVersion( )
	{
		return minorVersion;
	}

	public int getBugfixVersion( )
	{
		return bugfixVersion;
	}

	public boolean isCompatible( IPluginApiVersion versionOfPlugin )
	{
		if ( versionOfPlugin.getMajorVersion( ) != getMajorVersion( ) )
		{
			return false;
		}

		if ( versionOfPlugin.getMinorVersion( ) < this.getMinorVersion( ) )
		{
			return false;
		}

		return true;
	}

	@Override
	public String toString( )
	{
		return this.getMajorVersion( ) + "." + this.getMinorVersion( ) + "." + this.getBugfixVersion( );
	}
}
