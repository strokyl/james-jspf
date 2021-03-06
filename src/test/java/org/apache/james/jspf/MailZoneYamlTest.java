/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.jspf;

import org.apache.james.jspf.core.DNSService;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MailZoneYamlTest extends AbstractYamlTest {

    private static final String YAMLFILE2 = "mailzone-tests.yml";

    /**
     * @param name
     * @throws IOException
     */
    public MailZoneYamlTest(String name) throws IOException {
        super(name);
    }

    protected MailZoneYamlTest(SPFYamlTestSuite def, String test) {
        super(def, test);
    }

    protected MailZoneYamlTest(SPFYamlTestSuite def) {
        super(def);
    }

    protected String getFilename() {
        return YAMLFILE2;
    }

    public static Test suite() throws IOException {
        return new MailZoneSuite();
    }

    protected List internalLoadTests(String filename) throws IOException {
        return loadTests(filename);
    }

    protected DNSService getDNSService() {
        DNSService dns = super.getDNSService();
        // Remove record limits for this test
        dns.setRecordLimit(0);
        return dns;
    }

    static class MailZoneSuite extends TestSuite {

        public MailZoneSuite() throws IOException {
            super();
            List tests = loadTests(YAMLFILE2);
            Iterator i = tests.iterator();
            while (i.hasNext()) {
                SPFYamlTestSuite o = (SPFYamlTestSuite) i.next();
                Iterator ttt = o.getTests().keySet().iterator();
                while (ttt.hasNext()) {
                    addTest(new MailZoneYamlTest(o,(String) ttt.next()));
                }
            }
        }

    }

}
