package service.Controllers;

import DAOs.GetCurrentTime;
import Usecase.Activites.TrackActivity.Interactor;
import ui.ActivityPresenter.TrackActivityPresenter;
import ui.DashboardUI;

public class TrackActivityFactory {
    public static TrackActivity build(DashboardUI ui){
        TrackActivityPresenter trackActivityPresenter = new TrackActivityPresenter(ui);
        GetCurrentTime getCurrentTime = new GetCurrentTime();
        Interactor interactor = new Interactor(trackActivityPresenter, getCurrentTime);
        return new TrackActivity(interactor);
    }
}
