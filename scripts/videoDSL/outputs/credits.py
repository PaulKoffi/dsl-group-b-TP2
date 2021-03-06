# CinEditorML model Code
# Result Video Name: part5

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_endingText = TextClip(" By CinEditorML  ", fontsize=70, color='white')
_endingText = _endingText.set_position('center').set_duration(10)
endingText = CompositeVideoClip([_endingText], size=[1920, 1080])

blackmask = ImageClip('image_mountain.jpg').resize(0.2).set_duration(37)
w = 720
h = w * 9 / 16 
moviesize = w, h

txt = "Credits, Projet CinEditorML, dans le cadre du cours de, DSL, realise par,  Florian AINADOU,  Djotiham NABAGOU,  Paul KOFFI,  Paul Marie DJEKINNOU, ETUDIANTS en SI5, Specialisation AL, Nous tenons egalement , A remercier, Msr Julien DEANTONI, pour ce super cours, et ce projet , que nous avons adorer,       MERCI,       POUR,       TOUT".replace(",", "\n")
# Add blanks
txt = 10 * "\n" + txt + 10 * "\n"

creditsText = TextClip(txt, color='black', align='West', fontsize=25,
                    font='Xolonium-Bold', method='label')
creditsText = creditsText.set_position(lambda t: ('center', (-20 * (t + 5)))).set_duration(37)

creditsText = CompositeVideoClip([blackmask, creditsText.crossfadein(2)])


### AUDIO 

TEMPV = 0 + 0
audio_audioE = AudioFileClip("audioEnding.mp3")
audio_endingText = endingText.audio
if audio_endingText is None:
    compo = CompositeAudioClip([audio_audioE.set_start(TEMPV).volumex(2.0)])
else:
    compo = CompositeAudioClip([audio_endingText.volumex(1), audio_audioE.set_start(TEMPV).volumex(2.0)])
endingText = endingText.set_audio(compo)

# CREATE FINAL VIDEO

final = concatenate_videoclips([endingText, creditsText], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("part5.mp4", fps=30)

### REMOVE TEMP VIDEOS


