/*
 *  Copyright (C) 2014, Thomas Obenaus. All rights reserved.
 *  Licensed under the New BSD License (3-clause lic)
 *  See attached license-file.
 *
 *	Author: 	Thomas Obenaus
 *	EMail:		obenaus.thomas@gmail.com
 *  Project:    LogFileViewer
 */

package thobe.logfileviewer.plugin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import thobe.logfileviewer.plugin.source.logline.ILogLine;

/**
 * @author Thomas Obenaus
 * @source PatternMatch.java
 * @date Apr 13, 2015
 */
public class PatternMatch
{
	/**
	 * Returns true if the given string matches the given pattern, false otherwise.
	 * @param pattern
	 * @param line
	 * @return
	 */
	public static boolean matches( final Pattern pattern, final String line )
	{
		if ( pattern == null || pattern.pattern( ).trim( ).isEmpty( ) )
			return false;

		boolean result = false;

		try
		{
			Matcher m = pattern.matcher( line );
			result = m.matches( );
		}
		catch ( PatternSyntaxException e )
		{}

		return result;
	}

	/**
	 * Returns true if the given {@link ILogLine} matches the given pattern, false otherwise.
	 * @param pattern
	 * @param line
	 * @return
	 */
	public static boolean matches( final Pattern pattern, final ILogLine line )
	{
		return matches( pattern, line.getData( ) );
	}

}
