# CinEditorML model Code
# Result Video Name: part5

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Video 

videoEx = VideoFileClip('videoExtension.mp4')



TEMPV = 0 + 73
sV1 = TextClip("Appuyez sur la touche : F5 du clavier", fontsize=70, color='white')
sV1 = sV1.set_position('center').set_duration(6)
sV1 = sV1.set_start(TEMPV)


TEMPV = 0 + 106
sV2 = TextClip("Auto completion : CTRL + ESPACE", fontsize=70, color='white')
sV2 = sV2.set_position('center').set_duration(6)
sV2 = sV2.set_start(TEMPV)


TEMPV = 0 + 120
sV3 = TextClip("Execution : CTRL + SHIFT + P", fontsize=70, color='white')
sV3 = sV3.set_position('center').set_duration(3)
sV3 = sV3.set_start(TEMPV)


TEMPV = 0 + 123
sV4 = TextClip("et choisir ensuite GENERATE VIDEO", fontsize=70, color='white')
sV4 = sV4.set_position('center').set_duration(3)
sV4 = sV4.set_start(TEMPV)

# CREATE FINAL VIDEO

final = concatenate_videoclips([videoEx], method='compose')
final = CompositeVideoClip([final, sV1, sV2, sV3, sV4], size=[1920, 1080])

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("part5.mp4", fps=30)

### REMOVE TEMP VIDEOS


