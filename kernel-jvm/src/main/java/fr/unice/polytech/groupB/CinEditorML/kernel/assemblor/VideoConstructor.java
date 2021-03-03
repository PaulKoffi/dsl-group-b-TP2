package fr.unice.polytech.groupB.CinEditorML.kernel.assemblor;

import fr.unice.polytech.groupB.CinEditorML.kernel.behavioral.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.App;
import fr.unice.polytech.groupB.CinEditorML.kernel.structural.*;
import fr.unice.polytech.groupB.CinEditorML.kernel.utils.*;
import java.util.*;

/**
 * Quick and dirty visitor to support the generation of VideoCode
 */
public class VideoConstructor extends Visitor<StringBuffer> {

    ArrayList<String> utils = new ArrayList<>();
    ArrayList<String> sequence = new ArrayList<>();
    private LinkedHashMap<String, BackGroundElement> backGroundElements = new LinkedHashMap<>();
    private LinkedHashMap<String, String> subtitlesTime = new LinkedHashMap<>();
    private LinkedHashMap<String, FrontElement> frontElements = new LinkedHashMap<>();

    public VideoConstructor() {
        this.result = new StringBuffer();
    }

    private void wln(String s) {
        result.append(String.format("%s\n", s));
    }

    private void w(String s) {
        result.append(String.format("%s", s));
    }
    private boolean sub = false;
    private boolean absoluteAudio = false;

    @Override
    public void visit(App app) {
        //        wln("TEST");
        wln("# CinEditorML model Code");
        wln(String.format("# Result Video Name: %s", app.getName()));
        wln("");
        wln("from moviepy.editor import *");
        wln("import numpy as np");
        wln("from moviepy.video.tools.segmenting import findObjects");
        wln("from moviepy.video.io.VideoFileClip import VideoFileClip");
        wln("import os");
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

        // Ajout des helpers en cas d'animation
        LinkedHashSet<Animation> linkedset = new LinkedHashSet<>();
        for (String s : app.getSequence()) {
            BackGroundElement b = app.getBackGroundElements().get(s);
            if (b.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP_ANIMATION)) {
                TextClipWithAnimation textClipWithAnimation = (TextClipWithAnimation) b;
                linkedset.add(textClipWithAnimation.getAnimation());
            }
        }

        if (linkedset.size() > 0) {
            wln("# helper function");
            wln("rotMatrix = lambda a: np.array([[np.cos(a), np.sin(a)],\n" +
                    "                                [-np.sin(a), np.cos(a)]])");
            wln("");
            wln("");
            wln("# WE ANIMATE THE LETTERS");
            wln("def moveLetters(letters, funcpos):\n" +
                    "    return [letter.set_pos(funcpos(letter.screenpos, i, len(letters)))\n" +
                    "            for i, letter in enumerate(letters)]");
            wln("");
            for (Animation animation : linkedset) {
                if (animation.equals(Animation.ARRIVE)) {
                    wln("def arrive(screenpos, i, nletters):");
                    wln("    v = np.array([-1, 0])");
                    wln("    d = lambda t: max(0, 3 - 3 * t)");
                    wln("    return lambda t: screenpos - 400 * v * d(t - 0.2 * i)");
                    wln("");
                } else if (animation.equals(Animation.CASCADE)) {
                    wln("def cascade(screenpos, i, nletters):");
                    wln("    v = np.array([0, -1])");
                    wln("    d = lambda t: 1 if t < 0 else abs(np.sinc(t) / (1 + t ** 4))");
                    wln("    return lambda t: screenpos + v * 400 * d(t - 0.15 * i)");
                    wln("");
                } else if (animation.equals(Animation.VORTEX)) {
                    wln("def vortex(screenpos, i, nletters):");
                    wln("    d = lambda t: 1.0 / (0.3 + t ** 8)  # damping");
                    wln("    a = i * np.pi / nletters  # angle of the movement");
                    wln("    v = rotMatrix(a).dot([-1, 0])");
                    wln("    if i % 2: v[1] = -v[1]");
                    wln("    return lambda t: screenpos + 400 * d(t) * rotMatrix(0.5 * d(t) * a).dot(v)");
                    wln("");
                } else if (animation.equals(Animation.VORTEXOUT)) {
                    wln("def vortexout(screenpos, i, nletters):");
                    wln("    d = lambda t: max(0, t)  # damping");
                    wln("    a = i * np.pi / nletters  # angle of the movement");
                    wln("    v = rotMatrix(a).dot([-1, 0])");
                    wln("    if i % 2: v[1] = -v[1]");
                    wln("    return lambda t: screenpos + 400 * d(t - 0.1 * i) * rotMatrix(-0.2 * d(t) * a).dot(v)");
                    wln("");
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
            }else if (b.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP_ANIMATION)) {
                TextClipWithAnimation textClipWithAnimation = (TextClipWithAnimation) b;
                textClipWithAnimation.accept(this);
            }else if (b.getBackGroundElementType().equals(BackGroundElementType.TEXT_ENDING)) {
                EndingTextClip endingTextClip = (EndingTextClip) b;
                endingTextClip.accept(this);
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
        wln("# CREATE FINAL VIDEO");
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

        if(sub){
            w("final = CompositeVideoClip([final");
            for (String k : keysFront) {
                FrontElement f = app.getFrontElements().get(k);
                if (f.getFrontElementType().equals(FrontElementType.SUBTITLE)) {
                    w(String.format(", %s", f.getName()));
                }
            }
            w("], size=[1920, 1080])\n");
        }


        wln("");
        wln("### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)");
        for (String k : keysFront) {
            FrontElement f = app.getFrontElements().get(k);
            if (f.getFrontElementType().equals(FrontElementType.AUDIO)) {
                Audio audio = (Audio) f;
                if (audio.getTime().getType().equals(TimeType.ABSOLUTE)) {
                    AbsoluteTime absoluteTime = (AbsoluteTime) audio.getTime();
                    wln("");
//                    wln(String.format("final = VideoFileClip('%s.mp4')",app.getName()));
                    wln(String.format("audio_%s = AudioFileClip(\"%s\")", audio.getName(), audio.getPath()));
                    wln(String.format("audio_%s = %s.audio", "final","final"));
                    wln("if audio_final is None:");
                    wln(String.format("    compo = CompositeAudioClip([audio_%s.set_start(%d).volumex(%s)])", audio.getName(), absoluteTime.getTime(), String.valueOf(audio.getVolume()).replace(',','.')));
                    wln("else:");
                    wln(String.format("    compo = CompositeAudioClip([audio_%s.volumex(1), audio_%s.set_start(%d).volumex(%s)])", "final", audio.getName(), absoluteTime.getTime(), String.valueOf(audio.getVolume()).replace(',','.') ));
                    wln(String.format("%s = %s.set_audio(compo)", "final", "final"));
                }
            }
        }
        wln(String.format("final.write_videofile(\"%s.mp4\", fps=30)", app.getName()));

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


    @Override
    public void visit(TextClip textClip) {
        //        wln("# WE USE THE PLUGIN findObjects TO LOCATE AND SEPARATE EACH LETTER\n");
        //        wln("letters = findObjects(cvc)  # a list of ImageClips");
        wln("### Text ");
        wln("");
        wln(String.format("_%s = TextClip(\"%s\", fontsize=70, color='white')", textClip.getName(), textClip.getText()));
        wln(String.format("_%s = _%s.set_position('center').set_duration(%d)", textClip.getName(), textClip.getName(), textClip.getTime()));
        wln(String.format("%s = CompositeVideoClip([_%s], size=[1920, 1080])", textClip.getName(), textClip.getName()));
        wln("");
    }

    @Override
    public void visit(Audio audio) {
        if (!audio.getTime().getType().equals(TimeType.ABSOLUTE)) {
            wln("");
            wln("### AUDIO ");
            RelativeTime relativeTime = (RelativeTime) audio.getTime();
            common(relativeTime, audio.getName());
            wln(String.format("audio_%s = AudioFileClip(\"%s\")", audio.getName(), audio.getPath()));
            wln(String.format("audio_%s = %s.audio", relativeTime.getElement(), relativeTime.getElement()));
            //        String temp = "audio_" + relativeTime.getElement();
            wln(String.format("if audio_%s is None:", relativeTime.getElement()));
            wln(String.format("    compo = CompositeAudioClip([audio_%s.set_start(TEMPV).volumex(%s)])", audio.getName(), String.valueOf(audio.getVolume()).replace(',','.')));
            wln("else:");
            wln(String.format("    compo = CompositeAudioClip([audio_%s.volumex(1), audio_%s.set_start(TEMPV).volumex(%s)])", relativeTime.getElement(), audio.getName(), String.valueOf(audio.getVolume()).replace(',','.')));
            wln(String.format("%s = %s.set_audio(compo)", relativeTime.getElement(), relativeTime.getElement()));
        }
    }

    private void common(RelativeTime relativeTime, String name) {
        boolean isBackground = this.backGroundElements.containsKey(relativeTime.getElement());

        if (isBackground) {
            BackGroundElement b = this.backGroundElements.get(relativeTime.getElement());
            StringBuilder temp = new StringBuilder("TEMPV = 0");

            for (String s : this.sequence) {
                BackGroundElement backGroundElement = this.backGroundElements.get(s);
                // en fonction de la position au début
                if (s.equals(b.getName())) {

                    if (relativeTime.getPosition().equals(Position.BEFORE_BEGINNING)) {
                        temp.append(" - ").append(relativeTime.getTimeComparedToPosition());
                        break;
                    }

                    if (relativeTime.getPosition().equals(Position.AFTER_BEGINNING)) {
                        temp.append(" + ").append(relativeTime.getTimeComparedToPosition());
                        break;
                    }
                }

                if (backGroundElement.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP)) {
                    TextClip textClip = (TextClip) backGroundElement;
                    temp.append(" + ").append(textClip.getTime());
                }else if (backGroundElement.getBackGroundElementType().equals(BackGroundElementType.TEXT_CLIP_ANIMATION)) {
                    TextClipWithAnimation textClip = (TextClipWithAnimation) backGroundElement;
                    temp.append(" + ").append(textClip.getTime());
                }else if (backGroundElement.getBackGroundElementType().equals(BackGroundElementType.TEXT_ENDING)) {
                    EndingTextClip textClip = (EndingTextClip) backGroundElement;
                    temp.append(" + ").append(textClip.getTime());
                }else {
                    temp.append(" + int(").append(backGroundElement.getName()).append(".duration)");
                }


                // en fonction de la position à la fin
                if (s.equals(b.getName())) {
                    if (relativeTime.getPosition().equals(Position.BEFORE_ENDING)) {
                        temp.append(" - ").append(relativeTime.getTimeComparedToPosition());
                        break;
                    }

                    if (relativeTime.getPosition().equals(Position.AFTER_ENDING)) {
                        temp.append(" + ").append(relativeTime.getTimeComparedToPosition());
                        break;
                    }
                }
            }

            // Sauvegarde
            subtitlesTime.put(name, temp.toString());

            // Setup du subtitle
            wln("");
            wln(temp.toString());

        } else {
            FrontElement f = this.frontElements.get(relativeTime.getElement());
            StringBuilder temp2 = new StringBuilder(subtitlesTime.get(f.getName()));

            if (relativeTime.getPosition().equals(Position.BEFORE_BEGINNING)) {
                temp2.append(" - ").append(relativeTime.getTimeComparedToPosition());
            }

            if (relativeTime.getPosition().equals(Position.AFTER_BEGINNING)) {
                temp2.append(" + ").append(relativeTime.getTimeComparedToPosition());
            }

            if (relativeTime.getPosition().equals(Position.BEFORE_ENDING)) {
                temp2.append(" + ").append(f.getDuration()).append(" - ").append(relativeTime.getTimeComparedToPosition());
            }

            if (relativeTime.getPosition().equals(Position.AFTER_ENDING)) {
                temp2.append(" + ").append(f.getDuration()).append(" + ").append(relativeTime.getTimeComparedToPosition());
            }

            // Sauvegarde
            subtitlesTime.put(name, temp2.toString());

            // Setup du subtitle
            wln("");
            wln(temp2.toString());
        }
    }

    @Override
    public void visit(Subtitle subtitle) {
        this.sub = true;
        wln("");
        //        System.out.println(subtitle.getTime());
        if (subtitle.getTime().getType().equals(TimeType.ABSOLUTE)) {
            int t = 0;
            AbsoluteTime absoluteTime = (AbsoluteTime) subtitle.getTime();
            t = absoluteTime.getTime();

            // Setup du subtitle
            wln("");
            wln(String.format("%s = TextClip(\"%s\", fontsize=70, color='white')", subtitle.getName(), subtitle.getText()));
            wln(String.format("%s = %s.set_position(('center','bottom')).set_duration(%d)", subtitle.getName(), subtitle.getName(), subtitle.getDuration()));
            wln(String.format("%s = %s.set_start(%d)", subtitle.getName(), subtitle.getName(), t));
            // Sauvegarde
            subtitlesTime.put(subtitle.getName(), "TEMPV = " + t);
        } else {
            common((RelativeTime) subtitle.getTime(), subtitle.getName());
            wln(String.format("%s = TextClip(\"%s\", fontsize=70, color='white')", subtitle.getName(), subtitle.getText()));
            wln(String.format("%s = %s.set_position(('center','bottom')).set_duration(%d)", subtitle.getName(), subtitle.getName(), subtitle.getDuration()));
            wln(String.format("%s = %s.set_start(TEMPV)", subtitle.getName(), subtitle.getName()));
        }
    }


    @Override
    public void visit(SpecificVideoPart specificVideoPart) {
        wln("### Specific Video Part  ");
        wln("");
        wln(String.format("%s = %s.subclip('%s', '%s')", specificVideoPart.getName(), specificVideoPart.getPath(), specificVideoPart.getBeginning(), specificVideoPart.getEnding()));
        String path = specificVideoPart.getName() + ".mp4";
        wln(String.format("%s.write_videofile(\"%s\", fps=30)", specificVideoPart.getName(), path));
        wln("");
        this.utils.add(path);
    }

    @Override
    public void visit(TextClipWithAnimation textClip) {
        wln("### TextClip with Animation ");
        wln("");
        wln(String.format("_%s = TextClip(\"%s\", fontsize=70, color='white')", textClip.getName(), textClip.getText()));
        wln(String.format("_%s = _%s.set_position('center').set_duration(%d)", textClip.getName(), textClip.getName(), textClip.getTime()));
        wln(String.format("%s = CompositeVideoClip([_%s], size=[1920, 1080])", textClip.getName(), textClip.getName()));
        wln(String.format("letters = findObjects(%s)",textClip.getName()));
        wln(String.format("%s = CompositeVideoClip(moveLetters(letters, %s), size=[1920, 1080]).subclip(0, %d)", textClip.getName(), textClip.getAnimation().toString().toLowerCase(),textClip.getTime()));
        wln("");
    }

    @Override
    public void visit(EndingTextClip endingTextClip) {
        wln(String.format("blackmask = ImageClip('image_mountain.jpg').resize(0.2).set_duration(%d)",endingTextClip.getTime()));
        wln("w = 720");
        wln("h = w * 9 / 16 ");
        wln("moviesize = w, h");
        wln("");
        wln(String.format("txt = \"%s\".replace(\",\", \"\\n\")",endingTextClip.getText()));
        wln("# Add blanks");
        wln("txt = 10 * \"\\n\" + txt + 10 * \"\\n\"");
        wln("");
        wln(String.format("%s = TextClip(txt, color='white', align='West', fontsize=25,\n" +
                "                    font='Xolonium-Bold', method='label')", endingTextClip.getName()));
        wln(String.format("%s = %s.set_position(lambda t: ('center', (-20 * (t + 5)))).set_duration(%d)", endingTextClip.getName(), endingTextClip.getName(), endingTextClip.getTime()));
        wln("");
        wln(String.format("%s = CompositeVideoClip([blackmask, %s.crossfadein(2)])", endingTextClip.getName(),endingTextClip.getName()));
        wln("");
    }
}