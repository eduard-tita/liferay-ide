/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 ******************************************************************************/

package com.liferay.ide.hook.core.model.internal;

import com.liferay.ide.core.util.CoreUtil;
import com.liferay.ide.hook.core.model.Hook;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.sapphire.modeling.Path;
import org.eclipse.sapphire.services.RelativePathService;

/**
 * @author Gregory Amerson
 */
public class SrcFoldersRelativePathService extends RelativePathService
{

    @Override
    public List<Path> roots()
    {
        final List<Path> roots = new ArrayList<Path>();
        final Hook hook = context( Hook.class );

        if( hook != null )
        {
            final IProject project = hook.adapt( IProject.class );
            final List<IFolder> folders = CoreUtil.getSourceFolders( JavaCore.create( project ) );

            for( final IFolder folder : folders )
            {
                roots.add( new Path( folder.getLocation().toPortableString() ) );
            }
        }

        return roots;
    }
}
