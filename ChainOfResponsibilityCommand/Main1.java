interface Command {
    void execute();
}

class Light {

    public void turnOn() {
        System.out.println("Свет включен");
    }

    public void turnOff() {
        System.out.println("Свет выключен");
    }
}

class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {

        if (command != null) {
            command.execute();
        }
    }
}


public class Main1 {

    public static void main(String[] args) {

        // Receiver
        Light light = new Light();

        // Commands
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Включение света
        remote.setCommand(lightOn);
        remote.pressButton();

        // Выключение света
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}