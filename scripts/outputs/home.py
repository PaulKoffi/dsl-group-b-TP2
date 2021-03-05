# CinEditorML model Code
# Result Video Name: test

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_begin = TextClip("beginningText", fontsize=70, color='white')
_begin = _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])


# CREATE FINAL VIDEO

final = concatenate_videoclips([begin], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("test.mp4", fps=30)

### REMOVE TEMP VIDEOS


