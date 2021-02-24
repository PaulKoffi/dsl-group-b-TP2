# CinEditorML model Code
# Result Video Name: result2

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Video 

clip1 = VideoFileClip('V5.mp4')

### Text 

_begin = TextClip("beginningText", fontsize=70, color='white')
_begin = _begin.set_position(('center','bottom')).set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])

### Specific Video Part  

clip1a = clip1.subclip('00:23', '01:47')
clip1a.write_videofile("clip1a.mp4", fps=30)

### Specific Video Part  

clip1b = clip1.subclip('02:01', '02:21')
clip1b.write_videofile("clip1b.mp4", fps=30)

### Text 

_end = TextClip("endingText", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])



TEMPV = 0 + 10 + 10
s1 = TextClip(" I m goo", fontsize=70, color='white')
s1 = s1.set_position('center').set_duration(20)
s1 = s1.set_start(TEMPV)


TEMPV = 0 + 10 + 10 + 20 + 10
s2 = TextClip(" I m ba", fontsize=70, color='white')
s2 = s2.set_position('center').set_duration(30)
s2 = s2.set_start(TEMPV)


TEMPV = 0 + 10 + int(clip1a.duration) - 5
s3 = TextClip(" I m bad and goo", fontsize=70, color='white')
s3 = s3.set_position('center').set_duration(20)
s3 = s3.set_start(TEMPV)

# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, clip1a, clip1b, end], method='compose')
final = CompositeVideoClip([final, s1, s2, s3], size=[1920, 1080])
final.write_videofile("resultaaaa.mp4", fps=30)


### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)

### REMOVE TEMP VIDEOS
os.remove('clip1a.mp4')
os.remove('clip1b.mp4')


