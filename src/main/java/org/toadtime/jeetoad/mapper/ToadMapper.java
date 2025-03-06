package org.toadtime.jeetoad.mapper;

import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.entity.Toad;

public class ToadMapper {

    public static ToadResponse map(Toad toad) {
        if(toad == null)
            return null;
        return new ToadResponse(toad.getId(), toad.getAge(), toad.getGender(), toad.getName(), toad.getWeight(), toad.getDescription(), toad.getBirthday());
    }

    public static Toad map(CreateToad toad) {
        if(toad == null)
            return null;
        Toad newToad = new Toad();
        newToad.setAge(toad.age());
        newToad.setGender(toad.gender());
        newToad.setName(toad.name());
        newToad.setWeight(toad.weight());
        newToad.setDescription(toad.description());
        newToad.setBirthday(toad.birthday());
        return newToad;
    }
}
