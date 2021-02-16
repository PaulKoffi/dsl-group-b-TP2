package fr.unice.polytech.groupB.CinEditorML.kernel.assemblor;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.BackGroundElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.FrontElementType;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.Position;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.TimeType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class VideoConstructor extends Visitor<StringBuffer> {
    //    enum PASS {ONE, TWO}
//    private final static String CURRENT_STATE = "current_state";
//    private int nbState = 0;
//    private boolean tonality =false;
//    private boolean interrupt =false;
//    private int INT_MAX= 32000;
//    private ArrayList<TemporalTransition> temporalTransitions = new ArrayList<>();
//
//    private boolean utilForText = false;
    ArrayList<String> utils = new ArrayList<>();
    ArrayList<String> sequence = new ArrayList<>();
    private LinkedHashMap<String, BackGroundElement> backGroundElements = new LinkedHashMap<>();
    private LinkedHashMap<String, String> subtitlesTime = new LinkedHashMap<>();
    private LinkedHashMap<String,FrontElement> frontElements = new LinkedHashMap<>();

    public VideoConstructor() {
        this.result = new StringBuffer();
    }

    private void wln(String s) {
        result.append(String.format("%s\n", s));
    }

    private void w(String s) {
        result.append(String.format("%s", s));
    }


    @Override
    public void visit(App app) {
//        wln("TEST");
        wln("# CinEditorML model Code");
        wln(String.format("# Result Video Name: %s\n", app.getName()));
        wln("");
        wln("from moviepy.editor import *");
        wln("import numpy as np");
        wln("from moviepy.video.tools.segmenting import findObjects");
        wln("from moviepy.video.io.VideoFileClip import VideoFileClip");
        wln("import os");
        wln("");
        wln("");
        this.backGroundElements = app.getBackGroundElements();
        this.frontElements = app.getFrontElements();
        // Chargement des elements utiles mais pas dans la séquence finale
        Set<String> keys = app.getBackGroundElements().keySet();
        for (String k : keys) {
            if (!app.getSequence().contains(k)) {
                BackGroundElement b = app.getBackGroundElements().get(k);
                if (b.getBackGroundElementType().equals(BackGroundElementType.VIDEO)) {
                    Video video = (Video) b;
                    video.accept(this);
                } else if (b.getBackGroundElementType().equals(BackGroundElementType.SPECIFIC_VIDEO)) {
                    SpecificVideoPart specificVideoPart = (SpecificVideoPart) b;
                    specificVideoPart.accept(this);
                }
            }
        }

        this.sequence = app.getSequence();

        for (String s : app.getSequence()) {
            BackGroundElement b = app.getBackGroundElements().get(s);

                if (b.getBackGroundElementType().equals(BackGroundElementType.VIDEO)) {
                    Video video = (Video) b;
                    video.accept(this);
                } else if (b.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP)) {
                    TextClip textClip = (TextClip) b;
                    textClip.accept(this);
                } else if (b.getBackGroundElementType().equals(BackGroundElementType.SPECIFIC_VIDEO)) {
                    SpecificVideoPart specificVideoPart = (SpecificVideoPart) b;
                    specificVideoPart.accept(this);
                }
        }

        Set<String> keysFront = app.getFrontElements().keySet();

        for (String k : keysFront) {
            FrontElement f = app.getFrontElements().get(k);
            if (f.getFrontElementType().equals(FrontElementType.SUBTITLE)) {
                Subtitle subtitle = (Subtitle) f;
                subtitle.accept(this);
            } else if (f.getFrontElementType().equals(FrontElementType.AUDIO)) {
                Audio audio = (Audio) f;
                audio.accept(this);
            }
        }
        wln("");
        wln("");

        boolean first = true;
        //        Generate video
        w("final = concatenate_videoclips([");
        for (String s : app.getSequence()) {
            if (first) {
                w(String.format("%s", s));
                first = false;
            } else {
                w(String.format(", %s", s));
            }
        }


        w("], method='compose')\n");
        wln(String.format("final.write_videofile(\"%s.mp4\", fps=30)", app.getName()));
        wln("");
        wln("");
        wln("### REMOVE TEMP VIDEOS");
        for (String s : utils) {
            wln(String.format("os.remove('%s')", s));
        }
        wln("");
    }

    @Override
    public void visit(Video video) {
        wln("### Video ");
        wln("");
        wln(String.format("%s = VideoFileClip('%s')", video.getName(), video.getPath()));
        wln("");
    }

//    @Override
//    public void visit(Sequence sequence) {
//
//    }

    @Override
    public void visit(TextClip textClip) {
//        wln("####################################################################");
//        wln("# WE CREATE THE TEXT THAT IS GOING TO MOVE, WE CENTER IT.");
//        wln("");
//        wln("screensize = (720, 460)");
//        wln("txtClip = TextClip('Cool effect', color='white', font=\"Amiri-Bold\",\n" +
//                "                   kerning=5, fontsize=100)");
//        wln("cvc = CompositeVideoClip([txtClip.set_pos('center')],\n" +
//                "                         size=screensize)");
//        wln("");
//        wln("# THE NEXT FOUR FUNCTIONS DEFINE FOUR WAYS OF MOVING THE LETTERS");
//        wln("");
//        wln("");
//        wln("# helper function");
//        wln("rotMatrix = lambda a: np.array([[np.cos(a), np.sin(a)],\n" +
//                "                                [-np.sin(a), np.cos(a)]])");
//        wln("");
//        wln("");
//        wln("def vortex(screenpos, i, nletters):");
//        wln("    d = lambda t: 1.0 / (0.3 + t ** 8)  # damping");
//        wln("    a = i * np.pi / nletters  # angle of the movement");
//        wln("    v = rotMatrix(a).dot([-1, 0])");
//        wln("    if i % 2: v[1] = -v[1]");
//        wln("    return lambda t: screenpos + 400 * d(t) * rotMatrix(0.5 * d(t) * a).dot(v)");
//        wln("");
//        wln("");
//        wln("def cascade(screenpos, i, nletters):");
//        wln("    v = np.array([0, -1])");
//        wln("    d = lambda t: 1 if t < 0 else abs(np.sinc(t) / (1 + t ** 4))");
//        wln("    return lambda t: screenpos + v * 400 * d(t - 0.15 * i)");
//        wln("");
//        wln("");
//        wln("def arrive(screenpos, i, nletters):");
//        wln("    v = np.array([-1, 0])");
//        wln("    d = lambda t: max(0, 3 - 3 * t)");
//        wln("    return lambda t: screenpos - 400 * v * d(t - 0.2 * i)");
//        wln("");
//        wln("");
//        wln("def vortexout(screenpos, i, nletters):");
//        wln("    d = lambda t: max(0, t)  # damping");
//        wln("    a = i * np.pi / nletters  # angle of the movement");
//        wln("    v = rotMatrix(a).dot([-1, 0])");
//        wln("    if i % 2: v[1] = -v[1]");
//        wln("    return lambda t: screenpos + 400 * d(t - 0.1 * i) * rotMatrix(-0.2 * d(t) * a).dot(v)");
//        wln("");
//        wln("");
//        wln("# WE USE THE PLUGIN findObjects TO LOCATE AND SEPARATE EACH LETTER\n");
//        wln("letters = findObjects(cvc)  # a list of ImageClips");
//
        wln("### Text ");
        wln("");
        wln(String.format("_%s =  TextClip(\"%s\", fontsize=70, color='white')", textClip.getName(), textClip.getText()));
        wln(String.format("_%s =  _%s.set_position('center').set_duration(%d)", textClip.getName(), textClip.getName(), textClip.getTime()));
        wln(String.format("%s = CompositeVideoClip([_%s], size=[1920, 1080])", textClip.getName(), textClip.getName()));
        wln("");
    }

    @Override
    public void visit(Audio audio) {

    }

    @Override
    public void visit(Subtitle subtitle) {
        wln("");
//        System.out.println(subtitle.getTime());

        if (subtitle.getTime().getType().equals(TimeType.ABSOLUTE)) {
            int t = 0;
            AbsoluteTime absoluteTime = (AbsoluteTime) subtitle.getTime();
            t = absoluteTime.getTime();

            // Setup du subtitle
            wln("");
            wln(String.format("%s =  TextClip(\"%s\", fontsize=70, color='white')", subtitle.getName(), subtitle.getText()));
            wln(String.format("%s =  %s.set_position('center').set_duration(%d)", subtitle.getName(), subtitle.getName(), subtitle.getDuration()));
            wln(String.format("%s =  %s.set_start(%d)", subtitle.getName(), subtitle.getName(), t));
        } else {
            RelativeTime relativeTime = (RelativeTime) subtitle.getTime();
            boolean isBackground = this.backGroundElements.containsKey(relativeTime.getElement());

            if(isBackground){
                BackGroundElement b = this.backGroundElements.get(relativeTime.getElement());
                StringBuilder temp = new StringBuilder("TEMPV = 0 ");

                for (String s : this.sequence) {
                    BackGroundElement backGroundElement = this.backGroundElements.get(s);
                    // en fonction de la position au début
                    if (s.equals(b.getName())) {

                        if(relativeTime.getPosition().equals(Position.BEFORE_BEGINNING)){
                            temp.append(" - ").append(relativeTime.getTimeComparedToPosition());
                            break;
                        }

                        if(relativeTime.getPosition().equals(Position.AFTER_BEGINNING)){
                            temp.append(" + ").append(relativeTime.getTimeComparedToPosition());
                            break;
                        }
                    }

                        if (backGroundElement.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP)) {
                            TextClip textClip = (TextClip) backGroundElement;
                            temp.append(" + ").append(textClip.getTime());
                        } else {
                            temp.append(" +  int(").append(backGroundElement.getName()).append(".duration)");
                        }


                    // en fonction de la position à la fin
                    if (s.equals(b.getName())) {
                        if(relativeTime.getPosition().equals(Position.BEFORE_ENDING)){
                            temp.append(" - ").append(relativeTime.getTimeComparedToPosition());
                            break;
                        }

                        if(relativeTime.getPosition().equals(Position.AFTER_ENDING)){
                            temp.append(" + ").append(relativeTime.getTimeComparedToPosition());
                            break;
                        }
                    }
                }

                // Sauvegarde
                subtitlesTime.put(subtitle.getName(),temp.toString());

                // Setup du subtitle
                wln("");
                wln(temp.toString());

            }else{
                FrontElement f = this.frontElements.get(relativeTime.getElement());
                StringBuilder temp2 = new StringBuilder(subtitlesTime.get(f.getName()));

                if(relativeTime.getPosition().equals(Position.BEFORE_BEGINNING)){
                    temp2.append(" - ").append(relativeTime.getTimeComparedToPosition());
                }

                if(relativeTime.getPosition().equals(Position.AFTER_BEGINNING)){
                    temp2.append(" + ").append(relativeTime.getTimeComparedToPosition());
                }

                if(relativeTime.getPosition().equals(Position.BEFORE_ENDING)){
                    temp2.append(" + ").append(f.getDuration()).append(" - ").append(relativeTime.getTimeComparedToPosition());
                }

                if(relativeTime.getPosition().equals(Position.AFTER_ENDING)){
                    temp2.append(" + ").append(f.getDuration()).append(" + ").append(relativeTime.getTimeComparedToPosition());
                }

                // Sauvegarde
                subtitlesTime.put(subtitle.getName(),temp2.toString());

                // Setup du subtitle
                wln("");
                wln(temp2.toString());
            }
            wln(String.format("%s =  TextClip(\"%s\", fontsize=70, color='white')", subtitle.getName(), subtitle.getText()));
            wln(String.format("%s =  %s.set_position('center').set_duration(%d)", subtitle.getName(), subtitle.getName(), subtitle.getDuration()));
            wln(String.format("%s =  %s.set_start(TEMPV)", subtitle.getName(), subtitle.getName()));
        }
    }

    @Override
    public void visit(SpecificAudioPart specificAudioPart) {

    }

    @Override
    public void visit(SpecificVideoPart specificVideoPart) {
        wln("### Specific Video Part  ");
        wln("");
        wln(String.format("%s = VideoFileClip('%s').subclip('%s','%s')", specificVideoPart.getName(), specificVideoPart.getPath(), specificVideoPart.getBeginning(), specificVideoPart.getEnding()));
        String path = specificVideoPart.getName() + ".mp4";
        wln(String.format("%s.write_videofile(\"%s.mp4\", fps=30)", specificVideoPart.getName(), path));
        wln("");
        this.utils.add(path);
    }
}