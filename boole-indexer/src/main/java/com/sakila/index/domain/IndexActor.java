package com.boole.index.domain;

import com.boole.domain.Actor;
import org.joda.time.DateTime;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexActor extends AbstractIndexPerson {
    public IndexActor() {
    }

    public IndexActor(Actor actor) {

        if (actor != null) {
            setId(actor.getActorId());
            setLastName(actor.getLastName());
            setFirstName(actor.getFirstName());
            setCreated((actor.getLastUpdated() != null)
                    ? new DateTime(actor.getLastUpdated().getTime())
                    : new DateTime());
            setUpdated((actor.getLastUpdated() != null)
                    ? new DateTime(actor.getLastUpdated().getTime())
                    : new DateTime());
        }
    }
}
