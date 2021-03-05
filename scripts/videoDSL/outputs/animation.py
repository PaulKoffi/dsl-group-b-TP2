# CinEditorML model Code
# Result Video Name: temporal

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_texteV = TextClip("CinEditorMl cest egalement du decoupage de videos", fontsize=70, color='white')
_texteV = _texteV.set_position('center').set_duration(10)
texteV = CompositeVideoClip([_texteV], size=[1920, 1080])

### Video 

videoMulti = VideoFileClip('MultiState.mp4')

### Specific Video Part  

clip1 = videoMulti.subclip('00:00', '00:10')
clip1.write_videofile("clip1.mp4", fps=30)

### Specific Video Part  

clip1b = videoMulti.subclip('00:27', '00:30')
clip1b.write_videofile("clip1b.mp4", fps=30)

### Text 

_texteExt = TextClip("Prise en main facile", fontsize=70, color='white')
_texteExt = _texteExt.set_position('center').set_duration(10)
texteExt = CompositeVideoClip([_texteExt], size=[1920, 1080])


### AUDIO 

TEMPV = 0 + 0
audio_audioD = AudioFileClip("audioDecoupage.mp3")
audio_texteV = texteV.audio
if audio_texteV is None:
    compo = CompositeAudioClip([audio_audioD.set_start(TEMPV).volumex(2.0)])
else:
    compo = CompositeAudioClip([audio_texteV.volumex(1), audio_audioD.set_start(TEMPV).volumex(2.0)])
texteV = texteV.set_audio(compo)


TEMPV = 0 + 10 + int(videoMulti.duration) + 1
sV1 = TextClip("Alors ici 4eme scenario ", fontsize=70, color='white')
sV1 = sV1.set_position(('center','bottom')).set_duration(2)
sV1 = sV1.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + 4
sV2 = TextClip("Le multiStateAlarm on demarre la simulation ", fontsize=70, color='white')
sV2 = sV2.set_position(('center','bottom')).set_duration(3)
sV2 = sV2.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + 7
sV3 = TextClip("Appuie sur le bouton alarme allumee ", fontsize=70, color='white')
sV3 = sV3.set_position(('center','bottom')).set_duration(3)
sV3 = sV3.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + int(clip1.duration) + 0
sV4 = TextClip("Etat initial ", fontsize=70, color='white')
sV4 = sV4.set_position(('center','bottom')).set_duration(3)
sV4 = sV4.set_start(TEMPV)

# CREATE FINAL VIDEO

final = concatenate_videoclips([texteV, videoMulti, clip1, clip1b, texteExt], method='compose')
final = CompositeVideoClip([final, sV1, sV2, sV3, sV4], size=[1920, 1080])

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("temporal.mp4", fps=30)

### REMOVE TEMP VIDEOS
os.remove('clip1.mp4')
os.remove('clip1b.mp4')


