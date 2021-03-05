# CinEditorML model Code
# Result Video Name: part2

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_texteV = TextClip("CinEditorMl cest egalement du decoupage de videos", fontsize=70, color='white')
_texteV = _texteV.set_position('center').set_duration(10)
texteV = CompositeVideoClip([_texteV], size=[1920, 1080])

### Text 

_texteV9 = TextClip("Ci apres la video quon va decouper", fontsize=70, color='white')
_texteV9 = _texteV9.set_position('center').set_duration(2)
texteV9 = CompositeVideoClip([_texteV9], size=[1920, 1080])

### Video 

videoMulti = VideoFileClip('TimedTransition.mp4')

### Text 

_texteV1 = TextClip("Nous allons decouper la video precedente en prenant", fontsize=70, color='white')
_texteV1 = _texteV1.set_position('center').set_duration(5)
texteV1 = CompositeVideoClip([_texteV1], size=[1920, 1080])

### Text 

_texteV2 = TextClip("respectivement les 11 premieres secondes et les 3 dernieres", fontsize=70, color='white')
_texteV2 = _texteV2.set_position('center').set_duration(5)
texteV2 = CompositeVideoClip([_texteV2], size=[1920, 1080])

### Text 

_texteV3 = TextClip("Nous allons egalement placer les sous titres ", fontsize=70, color='white')
_texteV3 = _texteV3.set_position('center').set_duration(5)
texteV3 = CompositeVideoClip([_texteV3], size=[1920, 1080])

### Text 

_texteV4 = TextClip("pour chaque seconde de la video", fontsize=70, color='white')
_texteV4 = _texteV4.set_position('center').set_duration(5)
texteV4 = CompositeVideoClip([_texteV4], size=[1920, 1080])

### Specific Video Part  

clip1 = videoMulti.subclip('00:00', '00:11')
clip1.write_videofile("clip1.mp4", fps=30)

### Specific Video Part  

clip1b = videoMulti.subclip('00:29', '00:32')
clip1b.write_videofile("clip1b.mp4", fps=30)


### AUDIO 

TEMPV = 0 + 0
audio_audioD = AudioFileClip("audioDecoupage.mp3")
audio_texteV = texteV.audio
if audio_texteV is None:
    compo = CompositeAudioClip([audio_audioD.set_start(TEMPV).volumex(1.5)])
else:
    compo = CompositeAudioClip([audio_texteV.volumex(1), audio_audioD.set_start(TEMPV).volumex(1.5)])
texteV = texteV.set_audio(compo)


TEMPV = 0 + 10 + 2 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 2
sV1 = TextClip("Alors notre 6eme et dernier scenario ", fontsize=70, color='white')
sV1 = sV1.set_position(('center','bottom')).set_duration(2)
sV1 = sV1.set_start(TEMPV)


TEMPV = 0 + 10 + 2 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 5
sV2 = TextClip("Notre deuxieme extension le timed transition donc", fontsize=70, color='white')
sV2 = sV2.set_position(('center','bottom')).set_duration(1)
sV2 = sV2.set_start(TEMPV)


TEMPV = 0 + 10 + 2 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 7
sV3 = TextClip("Notre transition temporelle", fontsize=70, color='white')
sV3 = sV3.set_position(('center','bottom')).set_duration(1)
sV3 = sV3.set_start(TEMPV)


TEMPV = 0 + 10 + 2 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 9
sV4 = TextClip("On demarre la simulation lorsquon clique sur le bouton", fontsize=70, color='white')
sV4 = sV4.set_position(('center','bottom')).set_duration(1)
sV4 = sV4.set_start(TEMPV)


TEMPV = 0 + 10 + 2 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 10
sV5 = TextClip("Clic jattends et elle seteint toute seule", fontsize=70, color='white')
sV5 = sV5.set_position(('center','bottom')).set_duration(1)
sV5 = sV5.set_start(TEMPV)

# CREATE FINAL VIDEO

final = concatenate_videoclips([texteV, texteV9, videoMulti, texteV1, texteV2, texteV3, texteV4, clip1, clip1b], method='compose')
final = CompositeVideoClip([final, sV1, sV2, sV3, sV4, sV5], size=[1920, 1080])

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("part2.mp4", fps=30)

### REMOVE TEMP VIDEOS
os.remove('clip1.mp4')
os.remove('clip1b.mp4')


