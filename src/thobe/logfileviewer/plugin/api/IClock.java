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

import java.util.regex.Pattern;

import thobe.logfileviewer.plugin.source.logline.ILogLine;

/**
 * @author Thomas Obenaus
 * @source IClock.java
 * @date Apr 26, 2015
 */
public interface IClock
{
	public long getCurrentTime( );

	public long getElapsed( );

	public long getStart( );

	public void update( ILogLine ll );

	public Pattern getFilter( );

	public String getName( );

	public void reset( );
}
