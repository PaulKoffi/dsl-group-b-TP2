# CinEditorML model Code
# Result Video Name: scenario1

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_begin = TextClip("beginningText", fontsize=70, color='white')
_begin = _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])

### Video 

video1 = VideoFileClip('videoTest1.mp4')

### Video 

video2 = VideoFileClip('videoTest2.mp4')

### Text 

_end = TextClip("endingText", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(15)
end = CompositeVideoClip([_end], size=[1920, 1080])


# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, video1, video2, end], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("scenario1.mp4", fps=30)

### REMOVE TEMP VIDEOS


