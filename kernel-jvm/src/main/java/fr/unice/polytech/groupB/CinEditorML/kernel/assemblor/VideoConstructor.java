package fr.unice.polytech.groupB.CinEditorML.kernel.assemblor;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;

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
        wln("# Wiring code generated from an CinEditorML model");
        wln(String.format("# Result Video Name: %s\n", app.getName()));
        wln("");
        wln("from moviepy.editor import *");
        wln("import numpy as np");
        wln("from moviepy.video.tools.segmenting import findObjects");
        wln("");
        wln("");

        for(BackGroundElement backGroundElement: app.getBackGroundElements()){
            backGroundElement.accept(this);
        }

        boolean first = true;
        //        Generate video
        w("final = concatenate_videoclips([");
        for(BackGroundElement backGroundElement: app.getBackGroundElements()){
            if(first){
                w(String.format("%s", backGroundElement.getName()));
                first = false;
            }else{
                w(String.format(", %s", backGroundElement.getName()));
            }
        }
        w("], method='compose')\n");
        wln(String.format("final.write_videofile(\"%s.mp4\", fps=30)", app.getName()));
        wln("");
        wln("");
    }

    @Override
    public void visit(Video video) {
        wln("### Video ");
        wln("");
        wln(String.format("%s = VideoFileClip('%s')", video.getName(), video.getPath()));
        wln("");
    }

    @Override
    public void visit(Sequence sequence) {

    }

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
}