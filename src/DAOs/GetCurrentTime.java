package DAOs;

import Usecase.Activites.TrackActivity.DAI;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class GetCurrentTime implements DAI {
    final ZoneId zoneId = ZoneId.of("America/Ontario");
    public LocalDateTime getTime(){
        return LocalDateTime.now(zoneId);
    }
}
