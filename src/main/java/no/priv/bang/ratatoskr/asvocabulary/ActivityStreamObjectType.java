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

public enum ActivityStreamObjectType {
    Object(Names.OBJECT),
    Activity(Names.ACTIVITY),
    Accept(Names.ACCEPT),
    TentativeAccept(Names.TENTATIVE_ACCEPT),
    Add(Names.ADD),
    Invite(Names.INVITE),
    Travel(Names.TRAVEL),
    Collection(Names.COLLECTION),
    OrderedCollection(Names.ORDERED_COLLECTION),
    CollectionPage(Names.COLLECTION_PAGE),
    OrderedCollectionPage(Names.ORDERED_COLLECTION_PAGE),
    Application(Names.APPLICATION),
    Group(Names.GROUP),
    Organization(Names.ORGANIZATION),
    Person(Names.PERSON),
    Service(Names.SERVICE),
    Image(Names.IMAGE),
    Note(Names.NOTE),
    Event(Names.EVENT),
    Place(Names.PLACE),
    Link(Names.LINK);

    public class Names {
            public static final String OBJECT = "Object";
            public static final String ACTIVITY = "Activity";
            public static final String ACCEPT = "Accept";
            public static final String TENTATIVE_ACCEPT = "TentativeAccept";
            public static final String ADD = "Add";
            public static final String INVITE = "Invite";
            public static final String TRAVEL = "Travel";
            public static final String COLLECTION = "Collection";
            public static final String ORDERED_COLLECTION = "OrderedCollection";
            public static final String COLLECTION_PAGE = "CollectionPage";
            public static final String ORDERED_COLLECTION_PAGE = "OrderedCollectionPage";
            public static final String APPLICATION = "Application";
            public static final String GROUP = "Group";
            public static final String ORGANIZATION = "Organization";
            public static final String PERSON = "Person";
            public static final String SERVICE = "Service";
            public static final String IMAGE = "Image";
            public static final String NOTE = "Note";
            public static final String EVENT = "Event";
            public static final String PLACE = "Place";
            public static final String LINK = "Link";
    }

    private final String label;

    private ActivityStreamObjectType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
