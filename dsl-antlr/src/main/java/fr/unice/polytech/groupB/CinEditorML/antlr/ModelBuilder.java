package fr.unice.polytech.groupB.CinEditorML.antlr;

import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorBaseListener;
import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorParser;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.SpecificVideoPart;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.TextClip;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.Video;

public class ModelBuilder extends CinEditorBaseListener {

    String videoExtension = ".mp4";
    /********************
     ** Business Logic **
     ********************/


    private App theApp = null;
    private boolean built = false;

    public App retrieve() {
        if (built) { return theApp; }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    /*******************
     ** Symbol tables **
     *******************/



    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(CinEditorParser.RootContext ctx) {
        built = false;
        theApp = new App();
    }

    public void exitTextClip(CinEditorParser.TextClipContext ctx){
        TextClip textClip= new TextClip();
        textClip.setTime(Integer.parseInt(ctx.time.getText()));
        textClip.setText(ctx.text.getText());
        textClip.setName(ctx.name.getText());
        theApp.addBackGroundElement(textClip.getName(),textClip);
    }

    public void exitVideo(CinEditorParser.VideoContext ctx){
        Video video = new Video();
        video.setName(ctx.name.getText());
        video.setPath(ctx.path.getText());
        theApp.addBackGroundElement(video.getName(),video);
    }

    @Override
    public void exitRoot(CinEditorParser.RootContext ctx) {
        this.built = true;
    }

    @Override
    public void enterExport(CinEditorParser.ExportContext ctx) {
        theApp.setName(ctx.name.getText());
    }

    @Override
    public void exitCreateVideo(CinEditorParser.CreateVideoContext ctx){
        String[] text = ctx.list.getText().trim().split(",");
        for (String identifier : text) {
            theApp.getSequence().add(identifier);
        }
    }

    @Override
    public void exitSpecificPartOfVideo(CinEditorParser.SpecificPartOfVideoContext ctx){
        SpecificVideoPart part = new SpecificVideoPart();
        part.setName(ctx.name.getText());
        part.setPath(ctx.path.getText());
        part.setBeginning(ctx.start.getText());
        part.setEnding(ctx.end.getText());
    }


    @Override
    public void exitCutVideo(CinEditorParser.CutVideoContext ctx){
        SpecificVideoPart part = new SpecificVideoPart();
        part.setName(ctx.name.getText());
        part.setPath(ctx.source.getText()+ videoExtension);
        part.setBeginning(ctx.start.getText());
        part.setEnding(ctx.end.getText());
    }

//
//    @Override
//    public void enterSensor(CinEditorParser.SensorContext ctx) {
//        Sensor sensor = new Sensor();
//        sensor.setName(ctx.location().id.getText().substring(avoidSpecials,ctx.location().id.getText().length()-avoidSpecials));
//        sensor.setPin(Integer.parseInt(ctx.location().port.getText()));
//        this.theApp.getBricks().add(sensor);
//        sensors.put(sensor.getName(), sensor);
//    }
//
//    @Override
//    public void enterActuator(CinEditorParser.ActuatorContext ctx) {
//        Actuator actuator = new Actuator();
//        actuator.setName(ctx.location().id.getText().substring(avoidSpecials,ctx.location().id.getText().length()-avoidSpecials));
//        actuator.setPin(Integer.parseInt(ctx.location().port.getText()));
//        this.theApp.getBricks().add(actuator);
//        actuators.put(actuator.getName(), actuator);
//    }
//
//    @Override
//    public void enterState(CinEditorParser.StateContext ctx) {
//        State local = new State();
//        local.setName(ctx.name.getText().substring(avoidSpecials,ctx.name.getText().length()-avoidSpecials));
//        this.currentState = local;
//        if (ctx.tune!=null){
//            local.setTune(Tonality.valueOf(ctx.tune.getText().toUpperCase()).equals(Tonality.ON));
//        }
//
//        local.setId(stateId);
//        this.states.put(local.getName(), local);
//        stateId++;
//    }
//
//    @Override
//    public void exitState(CinEditorParser.StateContext ctx) {
//        this.theApp.getStates().add(this.currentState);
//        this.currentState = null;
//
//    }
//
//    @Override
//    public void enterAction(CinEditorParser.ActionContext ctx) {
//        Action action = new Action();
//        action.setActuator(actuators.get(ctx.receiver.getText()));
//        action.setValue(SIGNAL.valueOf(ctx.value.getText().toUpperCase()));
//        currentState.getActions().add(action);
//    }
//
//    @Override
//    public void enterTransition(CinEditorParser.TransitionContext ctx) {
//         //Creating a placeholder as the next state might not have been compiled yet.
//        Binding toBeResolvedLater = new Binding();
//        this.currentBinding = toBeResolvedLater;
//
//        toBeResolvedLater.next = states.get(ctx.end.getText());
//        toBeResolvedLater.from = states.get(ctx.begin.getText());
//        this.currentBinding = toBeResolvedLater;
//        this.currentBinding.conditionActions = new ArrayList<>();
//
//
//    }
//
//    @Override
//    public void exitTransition(CinEditorParser.TransitionContext ctx) {
//        Transition transition = new Transition();
//        transition.setNext(currentBinding.next);
//        transition.setFrom(currentBinding.from);
//        if (ctx.combination != null){
//            transition.setCondition(Condition.valueOf(ctx.combination.getText().toUpperCase()));
//        }
//
//        transition.setConditionActions(currentBinding.conditionActions);
//        bindings.put(ctx.begin.getText(), currentBinding);
//        theApp.getTransitions().add(transition);
//        this.currentBinding = null;
//
//    }
//
//    @Override
//    public void enterCombinationAction(CinEditorParser.CombinationActionContext ctx){
//        ConditionAction conditionAction =new ConditionAction();
//        conditionAction.setSensor(sensors.get(ctx.source.getText()));
//        conditionAction.setValue(SIGNAL.valueOf(ctx.value.getText().toUpperCase()));
//        currentBinding.conditionActions.add(conditionAction);
//    }
//
//    @Override
//    public void enterInitial(CinEditorParser.InitialContext ctx) {
//        this.theApp.setInitial(states.get(ctx.starting.getText()));
//    }
//
//    @Override
//    public void enterTonality(CinEditorParser.TonalityContext ctx){
//        this.theApp.setTonality(Tonality.valueOf(ctx.value.getText().toUpperCase()).equals(Tonality.ON));
//    }
//
//    @Override
//    public void enterInterrupt(CinEditorParser.InterruptContext ctx){
//        this.theApp.setInterrupt(Tonality.valueOf(ctx.value.getText().toUpperCase()).equals(Tonality.ON));
//    }
//
//    @Override
//    public void enterTemporal(CinEditorParser.TemporalContext ctx){
//        TemporalTransition temporalTransition = new TemporalTransition();
//        temporalTransition.setNext(states.get(ctx.end.getText()));
//        temporalTransition.setFrom(states.get(ctx.begin.getText()));
//        temporalTransition.setTime(Integer.parseInt(ctx.time.getText()));
//        theApp.getTransitions().add(temporalTransition);
//
//    }

}

