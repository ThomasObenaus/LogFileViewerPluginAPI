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
 * Interface that offers access to the LogStream in the way to register/unregister as listener.
 * Supported listeners: {@link ILogStreamDataListener}, {@link ILogStreamStateListener}.
 * @author Thomas Obenaus
 * @source ILogStreamAccess.java
 * @date Jun 1, 2014
 */
public interface ILogStreamAccess extends ILogStream
{
	/**
	 * Add a new {@link ILogStreamDataListener}.
	 * @param l
	 */
	public void addLogStreamDataListener( ILogStreamDataListener l );

	/**
	 * Remove a {@link ILogStreamDataListener}.
	 * @param l
	 */
	public void removeLogStreamDataListener( ILogStreamDataListener l );

	/**
	 * Add a new {@link ILogStreamStateListener}.
	 * @param l
	 */
	public void addLogStreamStateListener( ILogStreamStateListener l );

	/**
	 * Remove a {@link ILogStreamStateListener}.
	 * @param l
	 */
	public void removeLogStreamStateListener( ILogStreamStateListener l );
}
