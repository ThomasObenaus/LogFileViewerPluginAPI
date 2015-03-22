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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Thomas Obenaus
 * @source PluginApiVersion.java
 * @date Mar 7, 2015
 */
public class PluginApiVersion
{
	/**
	 * Increase for incompatible changes
	 */
	private static final int	MAJOR_VERSION	= 0;

	/**
	 * Increase for compatible changes
	 */
	private static final int	MINOR_VERSION	= 2;

	/**
	 * Increase for bugfixes
	 */
	private static final int	BUGFIX_VERSION	= 0;

	private static final String	ATTR_MAJOR		= "major";
	private static final String	ATTR_MINOR		= "minor";
	private static final String	ATTR_BUGFIX		= "bugfix";

	private int					majorVersion;
	private int					minorVersion;
	private int					bugfixVersion;

	public PluginApiVersion( )
	{
		this.majorVersion = MAJOR_VERSION;
		this.minorVersion = MINOR_VERSION;
		this.bugfixVersion = BUGFIX_VERSION;
	}

	public PluginApiVersion( File versionFile )
	{
		boolean bLoaded = false;
		if ( versionFile != null && versionFile.exists( ) && !versionFile.isDirectory( ) && versionFile.canRead( ) )
		{
			Properties props = new Properties( );
			try
			{
				props.load( new FileInputStream( versionFile ) );
				this.majorVersion = Integer.parseInt( props.getProperty( ATTR_MAJOR, "0" ) );
				this.minorVersion = Integer.parseInt( props.getProperty( ATTR_MINOR, "0" ) );
				this.bugfixVersion = Integer.parseInt( props.getProperty( ATTR_BUGFIX, "0" ) );
				bLoaded = true;
			}
			catch ( IOException | NumberFormatException e )
			{
				// could not read version-file
			}
		}

		if ( !bLoaded )
		{
			this.majorVersion = 0;
			this.minorVersion = 0;
			this.bugfixVersion = 0;
		}
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

	public boolean isCompatible( PluginApiVersion versionOfPlugin )
	{
		if ( versionOfPlugin.getMajorVersion( ) != getMajorVersion( ) )
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
