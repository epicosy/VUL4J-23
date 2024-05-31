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
package org.apache.sling.discovery.impl.cluster;

import static org.junit.Assert.assertEquals;

import org.apache.sling.discovery.TopologyView;
import org.apache.sling.discovery.base.its.AbstractTopologyEventTest;
import org.apache.sling.discovery.base.its.setup.VirtualInstanceBuilder;
import org.apache.sling.discovery.impl.setup.FullJR2VirtualInstanceBuilder;

public class TopologyEventTest extends AbstractTopologyEventTest {

    @Override
    public VirtualInstanceBuilder newBuilder() {
        return new FullJR2VirtualInstanceBuilder();
    }

    @Override
    public void assertEarlyAndFirstClusterViewIdMatches(TopologyView earlyTopo, TopologyView secondTopo) {
        assertEquals(earlyTopo.getLocalInstance().getClusterView().getId(),
                secondTopo.getLocalInstance().getClusterView().getId());
    }

    
}
