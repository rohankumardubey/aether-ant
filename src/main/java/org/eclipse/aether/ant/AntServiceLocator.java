/*******************************************************************************
 * Copyright (c) 2010, 2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.aether.ant;

import org.apache.maven.repository.internal.DefaultServiceLocator;
import org.apache.tools.ant.Project;
import org.eclipse.aether.RepositorySystem;

/**
 */
class AntServiceLocator
    extends DefaultServiceLocator
{

    private Project project;

    public AntServiceLocator( Project project )
    {
        this.project = project;
    }

    @Override
    protected void serviceCreationFailed( Class<?> type, Class<?> impl, Throwable exception )
    {
        String msg = "Could not initialize repository system";
        if ( !RepositorySystem.class.equals( type ) )
        {
            msg += ", service " + type.getName() + " (" + impl.getName() + ") failed to initialize";
        }
        msg += ": " + exception.getMessage();
        project.log( msg, exception, Project.MSG_ERR );
    }

}
