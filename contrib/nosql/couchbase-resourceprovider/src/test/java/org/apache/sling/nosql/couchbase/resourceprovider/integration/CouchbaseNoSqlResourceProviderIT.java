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
package org.apache.sling.nosql.couchbase.resourceprovider.integration;

import java.util.UUID;

import org.apache.jackrabbit.JcrConstants;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceProvider;
import org.apache.sling.nosql.couchbase.client.CouchbaseClient;
import org.apache.sling.nosql.couchbase.client.impl.CouchbaseClientImpl;
import org.apache.sling.nosql.couchbase.resourceprovider.impl.CouchbaseNoSqlResourceProviderFactory;
import org.apache.sling.nosql.generic.resource.impl.AbstractNoSqlResourceProviderTest;

import com.google.common.collect.ImmutableMap;

/**
 * Test basic ResourceResolver and ValueMap with different data types.
 */
public class CouchbaseNoSqlResourceProviderIT extends AbstractNoSqlResourceProviderTest {

    private Resource testRoot;

    @Override
    protected void registerResourceProviderFactory() {
        context.registerInjectActivateService(new CouchbaseClientImpl(), ImmutableMap.<String, Object>builder()
                        .put(CouchbaseClient.CLIENT_ID_PROPERTY, CouchbaseNoSqlResourceProviderFactory.COUCHBASE_CLIENT_ID)
                        .put("couchbaseHosts", System.getProperty("couchbaseHosts", "localhost:8091"))
                        .put("bucketName", System.getProperty("bucketName", "resource-test"))
                        .build());

        context.registerInjectActivateService(new CouchbaseNoSqlResourceProviderFactory(), ImmutableMap.<String, Object>builder()
                .put(ResourceProvider.ROOTS, "/test")
                .build());
    }

    @Override
    protected Resource testRoot() {
        if (this.testRoot == null) {
            try {
                Resource root = context.resourceResolver().getResource("/");
                Resource providerRoot = root.getChild("test");
                if (providerRoot == null) {
                    providerRoot = context.resourceResolver().create(root, "test",
                            ImmutableMap.<String, Object>of(JcrConstants.JCR_PRIMARYTYPE, JcrConstants.NT_UNSTRUCTURED));
                }
                this.testRoot = context.resourceResolver().create(providerRoot, UUID.randomUUID().toString(),
                        ImmutableMap.<String, Object>of(JcrConstants.JCR_PRIMARYTYPE, JcrConstants.NT_UNSTRUCTURED));
            }
            catch (PersistenceException ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.testRoot;
    }

}
