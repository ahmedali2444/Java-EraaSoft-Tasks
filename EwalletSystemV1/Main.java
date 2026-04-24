import services.ApplicationService;
import services.implementation.ApplicationServiceImpl;

public class Main {
    public static void main(String[] args){
        ApplicationService applicationService = new ApplicationServiceImpl();
        applicationService.start();
    }
}
