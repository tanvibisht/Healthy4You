package service.Controllers;

import Usecase.UpdateGPTcomment.GPTUpdateInput;

public class GPTcomment {
    private final GPTUpdateInput inputBound;

    public GPTcomment(GPTUpdateInput gptUpdateInput){
        inputBound = gptUpdateInput;
    }
    public void execute() {
        inputBound.excute();
    }
}
