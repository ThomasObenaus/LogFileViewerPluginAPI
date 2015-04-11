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
 * @source PluginException.java
 * @date Apr 11, 2015
 */
@SuppressWarnings ( "serial")
public class PluginException extends Exception
{
	public PluginException( String cause )
	{
		super( cause );
	}
}
