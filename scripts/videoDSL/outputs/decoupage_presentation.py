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


### AUDIO 

TEMPV = 0 + 0
audio_audioD = AudioFileClip("audioDecoupage.mp3")
audio_texteV = texteV.audio
if audio_texteV is None:
    compo = CompositeAudioClip([audio_audioD.set_start(TEMPV).volumex(1.5)])
else:
    compo = CompositeAudioClip([audio_texteV.volumex(1), audio_audioD.set_start(TEMPV).volumex(1.5)])
texteV = texteV.set_audio(compo)

# CREATE FINAL VIDEO

final = concatenate_videoclips([texteV, texteV9], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("part2.mp4", fps=30)

### REMOVE TEMP VIDEOS


