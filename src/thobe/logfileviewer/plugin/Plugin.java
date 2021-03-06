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

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JComponent;

import thobe.logfileviewer.plugin.api.IPlugin;
import thobe.logfileviewer.plugin.api.IPluginAccess;
import thobe.logfileviewer.plugin.api.IPluginPreferences;
import thobe.logfileviewer.plugin.api.IPluginUI;
import thobe.logfileviewer.plugin.api.IPluginUIComponent;
import thobe.logfileviewer.plugin.api.IPluginWindowManagerAccess;
import thobe.logfileviewer.plugin.api.PluginException;
import thobe.logfileviewer.plugin.source.logline.ILogLine;
import thobe.logfileviewer.plugin.source.logstream.ILogStream;
import thobe.logfileviewer.plugin.source.logstream.ILogStreamAccess;

/**
 * An abstract implementation of a {@link IPlugin} with a UI {@link IPluginUI}
 * @author Thomas Obenaus
 * @source Plugin.java
 * @date May 29, 2014
 */
public abstract class Plugin extends Thread implements IPluginUI, IPlugin
{
	private static final Pattern		ALL_FILTER	= Pattern.compile( ".*" );

	/**
	 * true if a stop was requested --> in this case the {@link Plugin} has to leave its run-method
	 */
	private AtomicBoolean				quitRequested;

	/**
	 * Internal instance of the logger
	 */
	private Logger						log;

	/**
	 * Name of the {@link Plugin}
	 */
	private String						pluginName;

	/**
	 * True if the {@link Plugin}s visual component is already attached to gui/mainframe
	 */
	private AtomicBoolean				attachedToGUI;

	/**
	 * Access to the window-management for {@link Plugin}s
	 */
	private IPluginWindowManagerAccess	pluginWindowMngAccess;

	/**
	 * Access to the {@link LogStream}.
	 */
	private ILogStreamAccess			logStreamAccess;

	/**
	 * The version of the plugin-api used for this plugin.
	 */
	private PluginApiVersion			apiVersion;

	/**
	 * If true, the {@link Plugin} will be started and added to gui and will get {@link ILogLine}s.
	 */
	private boolean						enabled;

	public Plugin( String pluginName, String logChannelName )
	{
		super( logChannelName );
		this.pluginName = pluginName;
		this.log = Logger.getLogger( logChannelName );
		this.quitRequested = new AtomicBoolean( false );
		this.attachedToGUI = new AtomicBoolean( false );
		this.logStreamAccess = null;
		this.apiVersion = new PluginApiVersion( );
		this.enabled = true;
	}

	void setApiVersion( PluginApiVersion apiVersion )
	{
		this.apiVersion = apiVersion;
	}

	protected Logger LOG( )
	{
		return this.log;
	}

	public boolean isQuitRequested( )
	{
		return quitRequested.get( );
	}

	@Override
	public boolean isVisible( )
	{
		JComponent vComp = this.getUIComponent( ).getVisualComponent( );
		if ( vComp == null )
		{
			LOG( ).severe( "Plugin '" + this.getPluginName( ) + "' is invalid since its visual-component is null" );
			return false;
		}
		return vComp.isVisible( );
	}

	@Override
	public void setVisible( boolean visible )
	{
		JComponent vComp = this.getUIComponent( ).getVisualComponent( );
		if ( vComp == null )
		{
			LOG( ).severe( "Plugin '" + this.getPluginName( ) + "' is invalid since its visual-component is null" );
		}
		vComp.setVisible( visible );
	}

	@Override
	public void setAttachedToGUI( boolean attached )
	{
		this.attachedToGUI.set( attached );
	}

	@Override
	public boolean isAttachedToGUI( )
	{
		return this.attachedToGUI.get( );
	}

	/**
	 * Quits the {@link Plugin} by setting the quitRequested variable. Each {@link Plugin} has to guarantee to leave its run-method as
	 * soon as possible. The state of this variable can be requested via {@link Plugin#isQuitRequested()}
	 */
	public void quit( )
	{
		this.quitRequested.set( true );
	}

	public String getPluginName( )
	{
		return this.pluginName;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null )
			return false;
		if ( !( obj instanceof Plugin ) )
			return false;

		Plugin other = ( Plugin ) obj;
		if ( ( this.getPluginName( ) == null && other.getPluginName( ) != null ) || ( this.getPluginName( ) != null && other.getPluginName( ) == null ) )
			return false;
		if ( !this.getPluginName( ).equals( other.getPluginName( ) ) )
			return false;

		if ( ( this.getPluginDescription( ) == null && other.getPluginDescription( ) != null ) || ( this.getPluginDescription( ) != null && other.getPluginDescription( ) == null ) )
			return false;
		if ( !this.getPluginDescription( ).equals( other.getPluginDescription( ) ) )
			return false;
		return true;
	}

	@Override
	public int hashCode( )
	{
		int hCPluginName = ( this.getPluginName( ) == null ) ? 0 : this.getPluginName( ).hashCode( );
		int hCPluginDesc = ( this.getPluginDescription( ) == null ) ? 0 : this.getPluginDescription( ).hashCode( );
		return hCPluginName * hCPluginDesc;
	}

	@Override
	public String toString( )
	{
		return this.getPluginName( ) + " {" + this.getPluginDescription( ) + "}";
	}

	@Override
	public final void setPluginWindowManagerAccess( IPluginWindowManagerAccess pWMA )
	{
		this.pluginWindowMngAccess = pWMA;
	}

	@Override
	public IPluginWindowManagerAccess getPluginWindowManagerAccess( )
	{
		return this.pluginWindowMngAccess;
	}

	@Override
	public ILogStream getLogstream( )
	{
		ILogStream result = null;
		synchronized ( this )
		{
			result = this.logStreamAccess;
		}
		return result;
	}

	@Override
	public final void onLogStreamAvailable( ILogStreamAccess logStreamAccess )
	{
		synchronized ( this )
		{
			this.logStreamAccess = logStreamAccess;
			if ( this.logStreamAccess == null )
			{
				LOG( ).severe( "Nullpointer as LogStream obtained, plugins will gain no data!" );
			}
			else
			{
				this.logStreamAccess.addLogStreamDataListener( this );
			}
		}// synchronized ( logStreamAccess )
	}

	@Override
	public final void onLogStreamLeavingScope( )
	{
		synchronized ( this )
		{
			if ( this.logStreamAccess != null )
			{
				this.logStreamAccess.removeLogStreamDataListener( this );
			}
			this.logStreamAccess = null;
		}// synchronized ( logStreamAccess )
	}

	@Override
	public String getLSRequesterName( )
	{
		return this.getPluginName( );
	}

	@Override
	public void response( int requestId, List<ILogLine> logLines, boolean valid )
	{
		throw new IllegalAccessError( "IlogStreamRequester.response() is not implemented. If you want to send a request to the LogStream you have to implement this method in your class." );
	}

	@Override
	public String getPluginVersion( )
	{
		return this.getMajorVersion( ) + "." + this.getMinorVersion( ) + "." + this.getBugfixVersion( );
	}

	public PluginApiVersion getPluginApiVersion( )
	{
		return apiVersion;
	}

	@Override
	public boolean isEnabled( )
	{
		return this.enabled;
	}

	@Override
	public void setEnabled( boolean enable )
	{
		this.enabled = enable;
	}

	@Override
	public IPluginPreferences getPluginPreferences( )
	{
		// per default return null --> no preferences for this plugin
		return null;
	}

	@Override
	public String getPluginAuthor( )
	{
		// per default return null --> no author for this plugin
		return null;
	}

	@Override
	public String getPluginAuthorEMailAddress( )
	{
		// per default return null --> no emailaddress for this plugin
		return null;
	}

	@Override
	public String getPluginLicense( )
	{
		// per default return null --> no license for this plugin
		return null;
	}

	@Override
	public String getPluginWebsite( )
	{
		// per default return null --> no website for this plugin
		return null;
	}

	@Override
	public String getPluginDescription( )
	{
		// per default return null --> no description for this plugin
		return null;
	}

	@Override
	public IPluginUIComponent getUIComponent( )
	{
		//  per default return null --> no gui
		return null;
	}

	@Override
	public Pattern getLineFilter( )
	{
		// per default return ALL_FILTER --> match all lines
		return ALL_FILTER;
	}

	@Override
	public void startPlugin( )
	{
		this.start( );
	}

	@Override
	public String getNameOfDataListener( )
	{
		return this.getPluginName( );
	}
	
	@Override
	public void waitForPluginStop( ) throws PluginException
	{
		try
		{
			this.join( );
		}
		catch ( InterruptedException e )
		{
			throw new PluginException( "Error calling join on this thread ('" + this.getPluginName( ) + "'): " + e.getLocalizedMessage( ) );
		}
	}

	@Override
	public String getNameOfMemoryWatchable( )
	{
		return this.getPluginName( );
	}

	@Override
	public boolean onStarted( )
	{
		// called on starting the plugin 
		// other plugins might no be available at this point
		return true;
	}

	@Override
	public boolean onRegistered( IPluginAccess pluginAccess )
	{
		// called when this plugin is registered 
		// other plugins are available at this point and can be
		// referenced via IPluginAccess
		return true;
	}

	@Override
	public void onLogStreamOpened( )
	{
		// called whenever a new logstream (file or over ip) is opened
	}

	@Override
	public void onPrepareCloseLogStream( )
	{
		// called whenever the current logstream will be closed
	}

	@Override
	public void onLogStreamClosed( )
	{
		// called whenever the current logstream is closed
	}

	@Override
	public void onUnRegistered( )
	{
		// called when this plugin is unregistered
	}

	@Override
	public boolean onStopped( )
	{
		// called when this plugin is stopped
		return true;
	}

}
