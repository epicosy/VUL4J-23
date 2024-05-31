/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.distribution.util;

import javax.annotation.Nonnull;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;

import org.apache.jackrabbit.api.observation.JackrabbitEvent;

/**
 * Utility class for JCR related distribution operations.
 */
public class DistributionJcrUtils {

    public static final String DO_NOT_DISTRIBUTE = "do.not.distribute";

    /**
     * checks a generated JCR event was not created by a JCR session having set the 'userData' to {@link #DO_NOT_DISTRIBUTE}.
     *
     * @param jcrEvent an {@link javax.jcr.observation.Event}
     * @return <code>false</code> if the event was generated by a {@link javax.jcr.Session} having its
     * {@link javax.jcr.observation.ObservationManager#setUserData(String)} set to {@link #DO_NOT_DISTRIBUTE}
     * @throws javax.jcr.RepositoryException if retrieving 'userData' fails
     */
    public static boolean isSafe(@Nonnull Event jcrEvent) throws RepositoryException {
        boolean safe = false;
        if (jcrEvent instanceof JackrabbitEvent && !((JackrabbitEvent) jcrEvent).isExternal()) {
            String userData = jcrEvent.getUserData();
            if (!DO_NOT_DISTRIBUTE.equals(userData)) {
                safe = true;
            }
        }
        return safe;

    }

    /**
     * set {@link #DO_NOT_DISTRIBUTE} on the given {@link Session}'s {@link javax.jcr.observation.ObservationManager#setUserData(String)}
     *
     * @param session a {@link javax.jcr.Session}
     * @throws RepositoryException if retrieving the {@link javax.jcr.observation.ObservationManager} fails
     */
    public static void setDoNotDistribute(@Nonnull Session session) throws RepositoryException {
        session.getWorkspace().getObservationManager().setUserData(DistributionJcrUtils.DO_NOT_DISTRIBUTE);
    }
}
