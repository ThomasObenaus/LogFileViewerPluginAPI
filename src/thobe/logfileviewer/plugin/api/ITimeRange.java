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

import thobe.logfileviewer.plugin.source.logline.ILogLine;

/**
 * @author Thomas Obenaus
 * @source ITimeRange.java
 * @date Apr 26, 2015
 */
public interface ITimeRange
{
	public void setEnd( long end );

	public boolean matches( IClock clock );

	public boolean matches( ILogLine ll );

	public long getEnd( );

	public long getStart( );

	public long getElapsed( );
}
