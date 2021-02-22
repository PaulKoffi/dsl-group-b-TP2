package fr.unice.polytech.groupB.CinEditorML.antlr;

import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorBaseListener;
import fr.unice.polytech.groupB.CinEditorML.antlr.grammar.CinEditorParser;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Animation;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Position;

import java.util.HashMap;
import java.util.Locale;

public class ModelBuilder extends CinEditorBaseListener {

    String videoExtension = ".mp4";
    /********************
     ** Business Logic **
     ********************/


    private App theApp = null;
    private boolean built = false;

    private final HashMap<String, Position> positions = new HashMap<String, Position>();

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
        positions.put("afterBeginning", Position.AFTER_BEGINNING);
        positions.put("beforeBeginning", Position.BEFORE_BEGINNING);
        positions.put("afterEnding", Position.AFTER_ENDING);
        positions.put("beforeEnding", Position.BEFORE_ENDING);
        theApp = new App();
    }

    @Override
    public void exitTextClip(CinEditorParser.TextClipContext ctx){
        if (ctx.animation!=null){
            if (ctx.animation.getText().toUpperCase().equals(Animation.SCROLL.toString())){
                EndingTextClip endingTextClip = new EndingTextClip();
                endingTextClip.setTime(Integer.parseInt(ctx.time.getText()));
                endingTextClip.setText(ctx.text.getText().substring(1,ctx.text.getText().length()-1 ));
                endingTextClip.setName(ctx.name.getText());
                theApp.addBackGroundElement(endingTextClip.getName(),endingTextClip);
            }
            else{
                TextClipWithAnimation textClipWithAnimation= new TextClipWithAnimation();
                textClipWithAnimation.setAnimation(Animation.valueOf(ctx.animation.getText().toUpperCase()));
                textClipWithAnimation.setTime(Integer.parseInt(ctx.time.getText()));
                textClipWithAnimation.setText(ctx.text.getText().substring(1,ctx.text.getText().length()-1 ));
                textClipWithAnimation.setName(ctx.name.getText());
                theApp.addBackGroundElement(textClipWithAnimation.getName(),textClipWithAnimation);
            }

        }
        else {
            TextClip textClip= new TextClip();
            textClip.setTime(Integer.parseInt(ctx.time.getText()));
            textClip.setText(ctx.text.getText().substring(1,ctx.text.getText().length()-1 ));
            textClip.setName(ctx.name.getText());


            theApp.addBackGroundElement(textClip.getName(),textClip);
        }

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

        int timeFinalValue = 0;
        String timeText =ctx.time.getText().trim();
        if (timeText.contains(":")){
            String[] time = timeText.split(":");

            timeFinalValue= Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        }
        else{
            timeFinalValue= Integer.parseInt(timeText);
        }

        if (ctx.position!=null){
            RelativeTime relativeTime = new RelativeTime();


            relativeTime.setElement(ctx.element.getText());
            relativeTime.setPosition(positions.get(ctx.position.getText()));
            relativeTime.setTimeComparedToPosition(timeFinalValue);
            audio.setTime(relativeTime);
        }

        else{
            AbsoluteTime absoluteTime = new AbsoluteTime();
            absoluteTime.setTime(timeFinalValue);
            audio.setTime(absoluteTime);

        }

        if (ctx.audioSound!=null){
            audio.setVolume(Float.parseFloat(ctx.audioSound.getText().substring(0,ctx.audioSound.getText().length()-1 )) );
        }

        theApp.addFrontElement(audio.getName(),audio);
    }

    @Override
    public void exitRoot(CinEditorParser.RootContext ctx) {
        this.built = true;
    }

    @Override
    public void exitExport(CinEditorParser.ExportContext ctx) {
        theApp.setName(ctx.name.getText());
    }

    @Override
    public void exitCreateVideo(CinEditorParser.CreateVideoContext ctx){
        int textLength = ctx.list.getText().length();
        String[] text = ctx.list.getText().substring(1, textLength-1).replaceAll("\\s+","").split(",");
        //String result = str.replaceAll("\\s+","");
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
    public void exitCutVideo(CinEditorParser.CutVideoContext ctx){
        SpecificVideoPart part = new SpecificVideoPart();
        part.setName(ctx.name.getText());
        part.setPath(ctx.source.getText());
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


        int timeFinalValue = 0;
        String timeText =ctx.time.getText().trim();
        if (timeText.contains(":")){
            String[] time = timeText.split(":");

            timeFinalValue= Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        }
        else{
            timeFinalValue= Integer.parseInt(timeText);
        }

        if (ctx.position!=null){
            RelativeTime relativeTime = new RelativeTime();


            relativeTime.setElement(ctx.element.getText());
            relativeTime.setPosition(positions.get(ctx.position.getText()));
            relativeTime.setTimeComparedToPosition(timeFinalValue);
            subtitle.setTime(relativeTime);
        }

        else{
            AbsoluteTime absoluteTime = new AbsoluteTime();
            absoluteTime.setTime(timeFinalValue);
            subtitle.setTime(absoluteTime);

        }

        timeText =ctx.duration.getText().trim();
        if (timeText.contains(":")){
            String[] time = timeText.split(":");

            timeFinalValue= Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        }
        else{
            timeFinalValue= Integer.parseInt(timeText);
        }


        subtitle.setDuration(timeFinalValue);

        theApp.addFrontElement(ctx.name.getText(), subtitle);
    }


}

