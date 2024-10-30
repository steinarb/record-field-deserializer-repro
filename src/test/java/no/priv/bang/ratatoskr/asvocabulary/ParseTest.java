/*
 * Copyright 2024 Steinar Bang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package no.priv.bang.ratatoskr.asvocabulary;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseTest {
    static ObjectMapper mapper = new ObjectMapper();

    @Test
    void testParseExample012() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_012.json"), LinkOrObject.class);
        switch(object) {
            case Add add -> {
                assertThat(add.summary()).isEqualTo("Sally added an object");
                switch (add.object()) {
                    case Link link -> assertThat(link.href()).isEqualTo("http://example.org/abc");
                    default -> fail("Did not get the expected type for add.object");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    private InputStream refactorExample(String classpathResource) {
        return this.getClass().getResourceAsStream("/json/activitystreams-vocabulary/" + classpathResource);
    }

}
