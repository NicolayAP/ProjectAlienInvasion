package presenters;

import views.Background;

public class Presenter {
    private Background background;

    public Presenter() {
        this.background = new Background();
    }

    public void start(){
        background.run();
    }

    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.start();
    }
}
