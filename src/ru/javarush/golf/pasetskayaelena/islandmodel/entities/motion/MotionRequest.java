package ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;

public final class MotionRequest {
    private final Location location;
    private MotionRequestStatus status;

    public MotionRequest(Location location, MotionRequestStatus status) {
        this.location = location;
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public MotionRequestStatus getStatus() {
        return status;
    }

    public void setStatus(MotionRequestStatus status) {
        this.status = status;
    }
}
