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
package org.apache.sling.launchpad.webapp.integrationtest.xml;

import java.io.IOException;

import org.apache.sling.commons.testing.integration.HttpTestBase;

/**
 * The <tt>DomTest</tt> verifies that simple DOM executions are successful
 * 
 */
public class DomTest extends HttpTestBase {


    public void testDomExecutionIsSuccessful() throws IOException {

        String content = getContent(HTTP_BASE_URL + "/bin/dom.xml", CONTENT_TYPE_PLAIN);

        assertEquals(content, "DOM");
    }

}
