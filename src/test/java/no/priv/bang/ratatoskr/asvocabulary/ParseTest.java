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
    void testParseExample001() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_001.json"), LinkOrObject.class);
        switch(object) {
            case ActivityStreamObject asobject -> {
                assertThat(asobject.id()).isEqualTo("http://www.test.example/object/1");
                assertThat(asobject.name()).isEqualTo("A Simple, non-specific object");
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample002() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_002.json"), LinkOrObject.class);
        switch(object) {
            case Link link -> {
                assertThat(link.href()).isEqualTo("http://example.org/abc");
                assertThat(link.hreflang()).isEqualTo("en");
                assertThat(link.mediaType()).isEqualTo("text/html");
                assertThat(link.name()).isEqualTo("An example link");
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample003() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_003.json"), LinkOrObject.class);
        switch(object) {
            case Activity activity -> {
                assertThat(activity.summary()).isEqualTo("Sally did something to a note");
                switch (activity.actor()) {
                    case Person person -> assertThat(person.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for activity.actor");
                }
                switch (activity.object()) {
                    case Note note -> assertThat(note.name()).isEqualTo("A Note");
                    default -> fail("Did not get the expected type for activity.object");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample004() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_004.json"), LinkOrObject.class);
        switch(object) {
            case Travel travel -> {
                assertThat(travel.summary()).isEqualTo("Sally went to work");
                switch (travel.actor()) {
                    case Person person -> assertThat(person.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for travel.actor");
                }
                switch (travel.target()) {
                    case Place place -> assertThat(place.name()).isEqualTo("Work");
                    default -> fail("Did not get the expected type for travel.target");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample005() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_005.json"), LinkOrObject.class);
        switch(object) {
            case Collection collection -> {
                assertThat(collection.summary()).isEqualTo("Sally's notes");
                assertThat(collection.totalItems()).isEqualTo(2);
                switch (collection.items().get(0)) {
                    case Note note -> assertThat(note.name()).isEqualTo("A Simple Note");
                    default -> fail("Did not get the expected type for collection.orderedItems[0]");
                }
                switch (collection.items().get(1)) {
                    case Note note -> assertThat(note.name()).isEqualTo("Another Simple Note");
                    default -> fail("Did not get the expected type for collection.orderedItems[1]");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample006() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_006.json"), LinkOrObject.class);
        switch(object) {
            case OrderedCollection collection -> {
                assertThat(collection.summary()).isEqualTo("Sally's notes");
                assertThat(collection.totalItems()).isEqualTo(2);
                switch (collection.orderedItems().get(0)) {
                    case Note note -> assertThat(note.name()).isEqualTo("A Simple Note");
                    default -> fail("Did not get the expected type for orderedCollection.orderedItems[0]");
                }
                switch (collection.orderedItems().get(1)) {
                    case Note note -> assertThat(note.name()).isEqualTo("Another Simple Note");
                    default -> fail("Did not get the expected type for orderedCollection.orderedItems[1]");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample007() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_007.json"), LinkOrObject.class);
        switch(object) {
            case CollectionPage collectionPage -> {
                assertThat(collectionPage.summary()).isEqualTo("Page 1 of Sally's notes");
                assertThat(collectionPage.id()).isEqualTo("http://example.org/foo?page=1");
                assertThat(collectionPage.partOf()).isEqualTo("http://example.org/foo");
                switch (collectionPage.items().get(0)) {
                    case Note note -> assertThat(note.name()).isEqualTo("A Simple Note");
                    default -> fail("Did not get the expected type for collectionPage.orderedItems[0]");
                }
                switch (collectionPage.items().get(1)) {
                    case Note note -> assertThat(note.name()).isEqualTo("Another Simple Note");
                    default -> fail("Did not get the expected type for collectionPage.orderedItems[1]");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample008() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_008.json"), LinkOrObject.class);
        switch(object) {
            case OrderedCollectionPage orderedCollectionPage -> {
                assertThat(orderedCollectionPage.summary()).isEqualTo("Page 1 of Sally's notes");
                assertThat(orderedCollectionPage.id()).isEqualTo("http://example.org/foo?page=1");
                assertThat(orderedCollectionPage.partOf()).isEqualTo("http://example.org/foo");
                switch (orderedCollectionPage.orderedItems().get(0)) {
                    case Note note -> assertThat(note.name()).isEqualTo("A Simple Note");
                    default -> fail("Did not get the expected type for orderedCollectionPage.orderedItems[0]");
                }
                switch (orderedCollectionPage.orderedItems().get(1)) {
                    case Note note -> assertThat(note.name()).isEqualTo("Another Simple Note");
                    default -> fail("Did not get the expected type for orderedCollectionPage.orderedItems[1]");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample009() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_009.json"), LinkOrObject.class);
        switch(object) {
            case Accept accept -> {
                assertThat(accept.summary()).isEqualTo("Sally accepted an invitation to a party");
                switch (accept.actor()) {
                    case Actor actor -> assertThat(actor.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for accept.actor");
                }
                switch (accept.object()) {
                    case Invite invite -> {
                        switch (invite.actor()) {
                            case Link link -> assertThat(link.href()).isEqualTo("http://john.example.org");
                            default -> fail("Did not get the expected type for invite.actor");
                        }
                        switch (invite.object()) {
                            case Event event -> assertThat(event.name()).isEqualTo("Going-Away Party for Jim");
                            default -> fail("Did not get the expected type for invite.object");
                        }
                    }
                    default -> fail("Did not get the expected type for accept.object");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample010() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_010.json"), LinkOrObject.class);
        switch(object) {
            case Accept accept -> {
                assertThat(accept.summary()).isEqualTo("Sally accepted Joe into the club");
                switch (accept.actor()) {
                    case Actor actor -> assertThat(actor.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for accept.actor");
                }
                switch (accept.object()) {
                    case Person person -> assertThat(person.name()).isEqualTo("Joe");
                    default -> fail("Did not get the expected type for accept.object");
                }
                switch (accept.target()) {
                    case Group group -> assertThat(group.name()).isEqualTo("The Club");
                    default -> fail("Did not get the expected type for accept.target");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample011() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_011.json"), LinkOrObject.class);
        switch(object) {
            case TentativeAccept tentativeAccept -> {
                assertThat(tentativeAccept.summary()).isEqualTo("Sally tentatively accepted an invitation to a party");
                switch (tentativeAccept.actor()) {
                    case Actor actor -> assertThat(actor.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for tentativeAccept.actor");
                }
                switch (tentativeAccept.object()) {
                    case Invite invite -> {
                        switch (invite.actor()) {
                            case Link link -> assertThat(link.href()).isEqualTo("http://john.example.org");
                            default -> fail("Did not get the expected type for invite.actor");
                        }
                        switch (invite.object()) {
                            case Event event -> assertThat(event.name()).isEqualTo("Going-Away Party for Jim");
                            default -> fail("Did not get the expected type for invite.object");
                        }
                    }
                    default -> fail("Did not get the expected type for tentativeAccept.object");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample012() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_012.json"), LinkOrObject.class);
        switch(object) {
            case Add add -> {
                assertThat(add.summary()).isEqualTo("Sally added an object");
                switch (add.actor()) {
                    case Actor actor -> assertThat(actor.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for add.actor");
                }
                switch (add.object()) {
                    case Link link -> assertThat(link.href()).isEqualTo("http://example.org/abc");
                    default -> fail("Did not get the expected type for add.object");
                }
            }
            default -> fail("Did not get the expected type when parsing");
        }
    }

    @Test
    void testParseExample013() throws Exception {
        LinkOrObject object = mapper.readValue(refactorExample("example_013.json"), LinkOrObject.class);
        switch(object) {
            case Add add -> {
                assertThat(add.summary()).isEqualTo("Sally added a picture of her cat to her cat picture collection");
                switch (add.actor()) {
                    case Actor actor -> assertThat(actor.name()).isEqualTo("Sally");
                    default -> fail("Did not get the expected type for add.actor");
                }
                switch (add.object()) {
                    case Image image -> {
                        assertThat(image.name()).isEqualTo("A picture of my cat");
                        assertThat(image.url().href()).isEqualTo("http://example.org/img/cat.png");
                    }
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
