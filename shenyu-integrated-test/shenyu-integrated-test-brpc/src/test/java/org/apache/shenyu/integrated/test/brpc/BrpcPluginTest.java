/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.integrated.test.brpc;

import com.google.gson.reflect.TypeToken;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.integratedtest.common.AbstractPluginDataInit;
import org.apache.shenyu.integratedtest.common.dto.BrpcTest;
import org.apache.shenyu.integratedtest.common.helper.HttpHelper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BrpcPluginTest extends AbstractPluginDataInit {
    
    @BeforeAll
    public static void setup() throws IOException {
        String pluginResult = initPlugin(PluginEnum.BRPC.getName(),
                "{\"corethreads\":0, \"threads\":2147483647, \"queues\":0, \"threadpool\":\"shared\"}");
        assertThat(pluginResult, Matchers.is("success"));
    }
    
    @Test
    public void testHelloWorld() throws IOException {
        BrpcTest response = HttpHelper.INSTANCE.getFromGateway("/brpc/getUser?userId=1001", new TypeToken<BrpcTest>() {
        }.getType());
        assertThat(response.getUserName(), is("User1"));
        assertThat(response.getUserId(), is(1001L));
    }
}
