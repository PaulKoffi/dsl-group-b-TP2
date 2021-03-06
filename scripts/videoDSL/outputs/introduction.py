# CinEditorML model Code
# Result Video Name: part1

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

# helper function
rotMatrix = lambda a: np.array([[np.cos(a), np.sin(a)],
                                [-np.sin(a), np.cos(a)]])


# WE ANIMATE THE LETTERS
def moveLetters(letters, funcpos):
    return [letter.set_pos(funcpos(letter.screenpos, i, len(letters)))
            for i, letter in enumerate(letters)]

def arrive(screenpos, i, nletters):
    v = np.array([-1, 0])
    d = lambda t: max(0, 3 - 3 * t)
    return lambda t: screenpos - 400 * v * d(t - 0.2 * i)

def vortex(screenpos, i, nletters):
    d = lambda t: 1.0 / (0.3 + t ** 8)  # damping
    a = i * np.pi / nletters  # angle of the movement
    v = rotMatrix(a).dot([-1, 0])
    if i % 2: v[1] = -v[1]
    return lambda t: screenpos + 400 * d(t) * rotMatrix(0.5 * d(t) * a).dot(v)

def cascade(screenpos, i, nletters):
    v = np.array([0, -1])
    d = lambda t: 1 if t < 0 else abs(np.sinc(t) / (1 + t ** 4))
    return lambda t: screenpos + v * 400 * d(t - 0.15 * i)

def vortexout(screenpos, i, nletters):
    d = lambda t: max(0, t)  # damping
    a = i * np.pi / nletters  # angle of the movement
    v = rotMatrix(a).dot([-1, 0])
    if i % 2: v[1] = -v[1]
    return lambda t: screenpos + 400 * d(t - 0.1 * i) * rotMatrix(-0.2 * d(t) * a).dot(v)

### Text 

_begin = TextClip("Video de presentation de CinEditorML By CinEditorML !", fontsize=70, color='white')
_begin = _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])

### TextClip with Animation 

_t1 = TextClip("cool effect", fontsize=70, color='white')
_t1 = _t1.set_position('center').set_duration(5)
t1 = CompositeVideoClip([_t1], size=[1920, 1080])
letters = findObjects(t1)
t1 = CompositeVideoClip(moveLetters(letters, arrive), size=[1920, 1080]).subclip(0, 5)

### TextClip with Animation 

_t2 = TextClip("cool effect", fontsize=70, color='white')
_t2 = _t2.set_position('center').set_duration(5)
t2 = CompositeVideoClip([_t2], size=[1920, 1080])
letters = findObjects(t2)
t2 = CompositeVideoClip(moveLetters(letters, vortex), size=[1920, 1080]).subclip(0, 5)

### TextClip with Animation 

_t3 = TextClip("cool effect", fontsize=70, color='white')
_t3 = _t3.set_position('center').set_duration(5)
t3 = CompositeVideoClip([_t3], size=[1920, 1080])
letters = findObjects(t3)
t3 = CompositeVideoClip(moveLetters(letters, cascade), size=[1920, 1080]).subclip(0, 5)

### TextClip with Animation 

_t4 = TextClip("cool effect", fontsize=70, color='white')
_t4 = _t4.set_position('center').set_duration(5)
t4 = CompositeVideoClip([_t4], size=[1920, 1080])
letters = findObjects(t4)
t4 = CompositeVideoClip(moveLetters(letters, vortexout), size=[1920, 1080]).subclip(0, 5)



TEMPV = 0 + 5
s5 = TextClip("Enjoy It !!!", fontsize=70, color='white')
s5 = s5.set_position(('center','bottom')).set_duration(5)
s5 = s5.set_start(TEMPV)


TEMPV = 0 + 10 + 0
s1 = TextClip("Animation de texte ARRIVE", fontsize=70, color='white')
s1 = s1.set_position(('center','bottom')).set_duration(5)
s1 = s1.set_start(TEMPV)


TEMPV = 0 + 10 + 5 + 0
s2 = TextClip("Animation de texte VORTEX", fontsize=70, color='white')
s2 = s2.set_position(('center','bottom')).set_duration(5)
s2 = s2.set_start(TEMPV)


TEMPV = 0 + 10 + 5 + 5 + 0
s3 = TextClip("Animation de texte CASCADE", fontsize=70, color='white')
s3 = s3.set_position(('center','bottom')).set_duration(5)
s3 = s3.set_start(TEMPV)


TEMPV = 0 + 10 + 5 + 5 + 5 + 0
s4 = TextClip("Animation de texte VORTEXOUT", fontsize=70, color='white')
s4 = s4.set_position(('center','bottom')).set_duration(5)
s4 = s4.set_start(TEMPV)

# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, t1, t2, t3, t4], method='compose')
final = CompositeVideoClip([final, s5, s1, s2, s3, s4], size=[1920, 1080])

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)

audio_audio1 = AudioFileClip("audioDebut.mp3")
audio_final = final.audio
if audio_final is None:
    compo = CompositeAudioClip([audio_audio1.set_start(0).volumex(2.0)])
else:
    compo = CompositeAudioClip([audio_final.volumex(1), audio_audio1.set_start(0).volumex(2.0)])
final = final.set_audio(compo)

audio_audio2 = AudioFileClip("audioA.mp3")
audio_final = final.audio
if audio_final is None:
    compo = CompositeAudioClip([audio_audio2.set_start(11).volumex(2.0)])
else:
    compo = CompositeAudioClip([audio_final.volumex(1), audio_audio2.set_start(11).volumex(2.0)])
final = final.set_audio(compo)
final.write_videofile("part1.mp4", fps=30)

### REMOVE TEMP VIDEOS


