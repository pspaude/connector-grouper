/*
 * Copyright (c) 2010-2023 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.polygon.connector.grouper;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.spi.ConfigurationProperty;
import org.identityconnectors.framework.spi.StatefulConfiguration;
import org.identityconnectors.framework.spi.operations.DiscoverConfigurationOp;

import java.util.HashSet;

public class GrouperConfiguration extends AbstractConfiguration implements StatefulConfiguration {

    private static final Log LOG = Log.getLog(GrouperConfiguration.class);

    private Integer connectionValidTimeout = 10;
    private String databaseName;
    private GuardedString password;
    private String loginName;
    private String port;
    private String host;


    @Override
    public void validate() {
        LOG.info("Execution of validate configuration method.");
        String messagePart = "One or more mandatory parameters are not set: ";
        HashSet<String> parameters = new HashSet<>();


        if (host != null && !host.isEmpty()) {
        } else {
            parameters.add("host");
        }

        if (port != null && !port.isEmpty()) {
        } else {
            parameters.add("port");
        }

        if (databaseName != null && !databaseName.isEmpty()) {
        } else {
            parameters.add("databaseName");
        }

        if (password != null) {
        } else {
            parameters.add("password");
        }

        if (loginName != null && !loginName.isEmpty()) {
        } else {
            parameters.add("loginName");
        }

        if (connectionValidTimeout == 10) {

            LOG.info("Connection validation timeout will be used with the value 10 seconds for connection" +
                    "validation.");
        }

        if (connectionValidTimeout == 10) {

            LOG.info("Connection validation timeout will be used with the value 10 seconds for connection" +
                    "validation.");
        }

        if (!parameters.isEmpty()) {

            throw new ConfigurationException(messagePart + parameters);
        }
    }

    @ConfigurationProperty(order = 2,
            displayMessageKey = "host.display",
            helpMessageKey = "host.help",
            required = true)

    public String getHost() {
        return this.host;
    }

    public void setHost(String value) {
        this.host = value;
    }

    @ConfigurationProperty(order = 3,
            displayMessageKey = "port.display",
            helpMessageKey = "port.help", required = true)
    public String getPort() {
        return this.port;
    }

    public void setPort(String value) {
        this.port = value;
    }


    @ConfigurationProperty(order = 4,
            displayMessageKey = "loginName.display",
            helpMessageKey = "loginName.help",
            required = true)
    public String getUserName() {
        return this.loginName;
    }

    public void setLoginName(String value) {
        this.loginName = value;
    }

    @ConfigurationProperty(order = 5, confidential = true,
            displayMessageKey = "password.display",
            helpMessageKey = "password.help",
            required = true)
    public GuardedString getPassword() {
        return this.password;
    }

    public void setPassword(GuardedString value) {
        this.password = value;
    }


    @ConfigurationProperty(order = 6,
            displayMessageKey = "databaseName.display",
            helpMessageKey = "databaseName.help",
            required = true)
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }


    @ConfigurationProperty(order = 7,
            displayMessageKey = "connectionValidTimeout.display",
            helpMessageKey = "connectionValidTimeout.help")
    public Integer getConnectionValidTimeout() {
        return connectionValidTimeout;
    }

    public void setConnectionValidTimeout(Integer connectionValidTimeout) {
        this.connectionValidTimeout = connectionValidTimeout;
    }

    @Override
    public void release() {

        connectionValidTimeout = null;
        databaseName = null;
        password.dispose();
        loginName = null;
        port = null;
        host = null;
    }
}