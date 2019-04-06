package core;


// An interface to allow control of multiple screens.

public interface ControlledScreen {

    // this method allows screens that implement Controlled Screen to set their parent
    public void setScreenParent(ScreensController screenPage);
}