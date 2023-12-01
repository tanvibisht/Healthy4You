package Usecase.UpdateGPTcomment;

import domain.LoggedUser;
import domain.User;
import service.GeoLocationService;
import service.WeatherService;

public class GPTUpdateInteractor implements GPTUpdateInput{
    final GPTUpdateOutput output;
    final GPTupdateDAI dai;

    GPTUpdateInteractor(GPTUpdateOutput out, GPTupdateDAI da){
        output = out;
        dai = da;
    }

    public void excute(){
        if (LoggedUser.getUser() == null){
            output.prepareFailView("User not found");
        }else{
            User user = LoggedUser.getUser();
            String city = new GeoLocationService().getCity();
            String weather = new WeatherService().getWeather(city);
            String comment = dai.getComment(user, weather);
            output.prepareSuccessView(comment);
        }
    }
}
