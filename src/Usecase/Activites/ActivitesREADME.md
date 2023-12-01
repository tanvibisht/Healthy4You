# ACTIVITIES USECASES

## Progress: 
All inner layers are completed, interface adapters and DAOs not implemented.

## How to use
- **UpdateDatabaseActivities**: use before any modification to User activities and before writing Activities to database
to prevent lost of synchronized data
- **CreateActivity**: Add a new activity to logged user given the informations.
- **CompleteActivity**: Complete the task given task index in list.
- **ShowActivityList**: send show activities request to presenter.
- **TrackActivity**: used in the main loop when everytime the frame updates, sends list of uncompleted activities in 
Next hour and any missed before.
