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

package com.evolveum.polygon.connector.grouper.util;

import com.evolveum.polygon.connector.grouper.GrouperConnector;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.Schema;
import org.identityconnectors.framework.common.objects.SchemaBuilder;

public class SchemaTranslator {
    private static final Log LOG = Log.getLog(SchemaTranslator.class);
    public Schema generateSchema(){
        LOG.info("Evaluating the schema operation");


        SchemaBuilder schemaBuilder = new SchemaBuilder(GrouperConnector.class);
        GroupProcessing groupProcessing = new GroupProcessing();
        SubjectProcessing userProcessing = new SubjectProcessing();

        groupProcessing.buildObjectClass(schemaBuilder);
        userProcessing.buildObjectClass(schemaBuilder);

        // TODO schema limitation ? Maybe useful in list all
        //schemaBuilder.defineOperationOption(OperationOptionInfoBuilder.buildAttributesToGet(), SearchOp.class);

        return schemaBuilder.build();
    }
}