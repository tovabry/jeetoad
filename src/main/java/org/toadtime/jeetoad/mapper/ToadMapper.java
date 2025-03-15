package org.toadtime.jeetoad.mapper;

import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.dto.ToadResponse;
import org.toadtime.jeetoad.dto.UpdateToad;
import org.toadtime.jeetoad.entity.Toad;

public class ToadMapper {

    private ToadMapper() {}

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

    public static Toad map(UpdateToad updateToad, Toad existingToad) {
        if(updateToad.name() != null)
            existingToad.setName(updateToad.name());
        if (updateToad.description() != null)
            existingToad.setDescription(updateToad.description());
        if (updateToad.birthday() != null)
            existingToad.setBirthday(updateToad.birthday());
        if (updateToad.age() != null)
            existingToad.setAge(updateToad.age());
        if(updateToad.gender() != null)
            existingToad.setGender(updateToad.gender());
        if(updateToad.weight() != null)
            existingToad.setWeight(updateToad.weight());
        return existingToad;
    }
}
