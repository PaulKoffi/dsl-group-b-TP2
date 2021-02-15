package fr.unice.polytech.groupB.CinEditorML.antlr;

import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorBaseListener;
import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorParser;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Position;

import java.util.Locale;

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

    @Override
    public void exitTextClip(CinEditorParser.TextClipContext ctx){
        TextClip textClip= new TextClip();
        textClip.setTime(Integer.parseInt(ctx.time.getText()));
        textClip.setText(ctx.text.getText());
        textClip.setName(ctx.name.getText());
        theApp.addBackGroundElement(textClip.getName(),textClip);
    }

    @Override
    public void exitVideo(CinEditorParser.VideoContext ctx){
        Video video = new Video();
        video.setName(ctx.name.getText());
        video.setPath(ctx.path.getText());
        theApp.addBackGroundElement(video.getName(),video);
    }

    @Override
    public void exitAudio(CinEditorParser.AudioContext ctx){
        Audio audio = new Audio();
        audio.setName(ctx.name.getText());
        audio.setPath(ctx.path.getText());
        RelativeTime relativeTime = new RelativeTime();


        relativeTime.setElement(ctx.element.getText());
        int timeFinalValue = 0;
        String timeText =ctx.time.getText().trim();
        if (timeText.contains(":")){
            String[] time = timeText.split(":");

            timeFinalValue= Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        }
        else{
            timeFinalValue= Integer.parseInt(timeText);
        }

        relativeTime.setTimeComparedToPosition(timeFinalValue);


        audio.setTime(relativeTime);
        theApp.addFrontElement(audio.getName(),audio);
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
        theApp.addBackGroundElement(ctx.name.getText(), part);
    }

    @Override
    public void exitSpecificPartOfAudio(CinEditorParser.SpecificPartOfAudioContext ctx){
        SpecificAudioPart part = new SpecificAudioPart();
        part.setName(ctx.name.getText());
        part.setPath(ctx.path.getText());
        part.setBeginning(ctx.start.getText());
        part.setEnding(ctx.end.getText());
        theApp.addFrontElement(ctx.name.getText(), part);
    }

    @Override
    public void exitCutVideo(CinEditorParser.CutVideoContext ctx){
        SpecificVideoPart part = new SpecificVideoPart();
        part.setName(ctx.name.getText());
        part.setPath(ctx.source.getText()+ videoExtension);
        part.setBeginning(ctx.start.getText());
        part.setEnding(ctx.end.getText());
        theApp.addBackGroundElement(ctx.name.getText(), part);
    }


    @Override
    public void exitSubtitle(CinEditorParser.SubtitleContext ctx){
        Subtitle subtitle = new Subtitle();
        subtitle.setName(ctx.name.getText());
        int textLength = ctx.value.getText().length();
        subtitle.setText(ctx.value.getText().substring(1, textLength-2));
        RelativeTime relativeTime = new RelativeTime();

        relativeTime.setElement(ctx.element.getText());
        int timeFinalValue = 0;
        String timeText =ctx.time.getText().trim();
        if (timeText.contains(":")){
            String[] time = timeText.split(":");

            timeFinalValue= Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        }
        else{
            timeFinalValue= Integer.parseInt(timeText);
        }

        relativeTime.setTimeComparedToPosition(timeFinalValue);


        subtitle.setTime(relativeTime);
        theApp.addFrontElement(ctx.name.getText(), subtitle);
    }


}

