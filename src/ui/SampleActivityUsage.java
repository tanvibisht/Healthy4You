package ui;

import DAOs.ActivitiesDAO.ActivityReader;
import DAOs.ActivitiesDAO.ActivitySaver;
import DAOs.GetCurrentTime;
import Usecase.Activites.CompleteActivity.Interactor;
import Usecase.Activites.CompleteActivity.Output;
import Usecase.Activites.CompleteActivity.OutputData;
import service.Controllers.CompleteActivity;
import service.Controllers.SaveActivity;
import service.Controllers.TrackActivity;
import service.Controllers.UpdateDatabaseActivities;

public class SampleActivityUsage {
    final Object sampleUI = new Object();
    //1. first look at controller and output boundary, controller takes all attributes needed,
    //output will be given any attributes needs to present to the user
    //2. Write a presenter implementing outputboundary for the usecase.
    //for example, the show activity list presenter implements 2 methods and takes the ui page that you
    //want to modify as an instance, for example successview tells the ui to add activity panels,
    // failview bumps out a window for error message.
    //3. Now you have everything to intiallize the controller, intiallize using following codes

    public class SampleCompleteActivityPresenter implements Output {

        final Object ui; // needs to be Ui class when you want to operate in real case

        public SampleCompleteActivityPresenter(Object u){
            ui = u;
        }

        @Override
        public void prepareFailView(String error) {
        }

        @Override
        public void prepareSuccessView(OutputData message) {
        }
    }

    public void completeActivityButtonPressed(int activityIndex){
        SampleCompleteActivityPresenter sampleCompleteActivityPresenter = new SampleCompleteActivityPresenter(sampleUI);
        Interactor completeInteractor = new Interactor(sampleCompleteActivityPresenter);
        CompleteActivity completeActivity = new CompleteActivity(completeInteractor);
        //now the Controller completeActivity is initialized.
        completeActivity.execute(activityIndex);// when you need to use the function
    }

    public class SampleTrackActivityPresenter implements Usecase.Activites.TrackActivity.Output {
        final Object ui;

        public SampleTrackActivityPresenter(Object u){
            ui = u;
        }

        @Override
        public void prepareFailView(String message) {

        }

        @Override
        public void prepareSuccessView(Usecase.Activites.TrackActivity.OutputData output) {

        }
    }

    public void SampleTrackActivityButtonPressed(){
        SampleTrackActivityPresenter sampleTrackActivityPresenter = new SampleTrackActivityPresenter(sampleUI);
        GetCurrentTime trackActivityDAO = new GetCurrentTime();
        Usecase.Activites.TrackActivity.Interactor trackActivityInteractor = (
                new Usecase.Activites.TrackActivity.Interactor(sampleTrackActivityPresenter, trackActivityDAO));
        TrackActivity trackActivity = new TrackActivity(trackActivityInteractor);

        trackActivity.execute(); //when needed
    }

    public class SampleUpdateActivityPresenter implements Usecase.Activites.UpdateDatabaseActivities.Output {
        final Object ui;
        SampleUpdateActivityPresenter(Object u){
            ui = u;
        }
        @Override
        public void prepareSuccessView(String message) {

        }

        @Override
        public void prepareFailView(String message) {

        }
    }

    public void updateDatabaseActivitySample(){
        SampleUpdateActivityPresenter sampleUpdateActivityPresenter = new SampleUpdateActivityPresenter(sampleUI);
        ActivityReader activityReader = new ActivityReader();
        Usecase.Activites.UpdateDatabaseActivities.Interactor updateDatabaseInteractor = (
                new Usecase.Activites.UpdateDatabaseActivities.Interactor(sampleUpdateActivityPresenter,activityReader)
        );
        UpdateDatabaseActivities updateDatabaseActivities = new UpdateDatabaseActivities(updateDatabaseInteractor);

        updateDatabaseActivities.execute();
    }

    public class SampleSaveActivitiesPresenter implements Usecase.Activites.SaveActivities.Output{
        final Object ui;

        SampleSaveActivitiesPresenter(Object u){
            ui = u;
        }
        @Override
        public void prepareFailView(String message) {

        }

        @Override
        public void prepareSuccessView(String message) {

        }
    }

    public void sampleSaveActivity(){
        SampleSaveActivitiesPresenter sampleSaveActivitiesPresenter = new SampleSaveActivitiesPresenter(sampleUI);
        ActivitySaver activitySaver = new ActivitySaver();
        Usecase.Activites.SaveActivities.Interactor saveActivitiesInteractor = (
                new Usecase.Activites.SaveActivities.Interactor(sampleSaveActivitiesPresenter, activitySaver));
        SaveActivity saveActivity = new SaveActivity(saveActivitiesInteractor);

        saveActivity.execute();
    }

}
