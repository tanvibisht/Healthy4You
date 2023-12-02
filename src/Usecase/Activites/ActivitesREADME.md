# ACTIVITIES USECASES

## Progress: 
All interface adapters finished. Needs Ui implements(implement output boundary)

## How to use
- See sevice/controller, in main program first create and store new controller giving it the
corresponding boundary, then use execute function of this controller.

## Explaination
- **UpdateDatabaseActivities**: use before any modification to User activities and before writing Activities to database
to prevent lost of synchronized data
- **CreateActivity**: Add a new activity to logged user given the informations.
- **CompleteActivity**: Complete the task given task index in list.
- **ShowActivityList**: send show activities request to presenter.
- **TrackActivity**: used in the main loop when everytime the frame updates, sends list of uncompleted activities in 
Next hour and any missed before.

