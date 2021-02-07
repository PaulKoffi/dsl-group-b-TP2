package fr.unice.polytech.groupB.CinEditorML.kernel.behavioral;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.SIGNAL;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Sensor;

public class ConditionAction {
    private Sensor sensor;
    private SIGNAL value;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SIGNAL getValue() {
        return value;
    }

    public void setValue(SIGNAL value) {
        this.value = value;
    }
}
