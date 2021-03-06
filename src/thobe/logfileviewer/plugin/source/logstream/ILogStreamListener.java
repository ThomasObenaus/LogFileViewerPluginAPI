/*
 *  Copyright (C) 2014, Thomas Obenaus. All rights reserved.
 *  Licensed under the New BSD License (3-clause lic)
 *  See attached license-file.
 *
 *	Author: 	Thomas Obenaus
 *	EMail:		obenaus.thomas@gmail.com
 *  Project:    LogFileViewer
 */

package thobe.logfileviewer.plugin.source.logstream;

/**
 * @author Thomas Obenaus
 * @source LogStreamListener.java
 * @date Jun 1, 2014
 */
public interface ILogStreamListener
{
	/**
	 * Implement this method to identify the name of the {@link ILogStreamListener}.
	 * @return
	 */
	public String getLogStreamListenerName( );
}
