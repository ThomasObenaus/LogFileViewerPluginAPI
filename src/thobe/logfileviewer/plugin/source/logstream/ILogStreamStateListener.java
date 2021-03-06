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
 * Listener that can be used to monitor the states of a {@link LogStream}.
 * @author Thomas Obenaus
 * @source LogStreamStateListener.java
 * @date Jun 1, 2014
 */
public interface ILogStreamStateListener extends ILogStreamListener
{
	/**
	 * Called whenever the end of the {@link LogStream}/ log-file (EOF) was reached.
	 */
	public void onEOFReached( );

	/**
	 * Called whenever a new {@link LogStream}/ log-file was opened.
	 */
	public void onOpened( );

	/**
	 * Called whenever a new {@link LogStream}/ log-file was closed.
	 */
	public void onClosed( );
}
