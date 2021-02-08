# Wiring code generated from an CinEditorML model
# Result Video Name: result


from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects


### Text 

_begin =  TextClip("beginningText", fontsize=70, color='white')
_begin =  _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])

### Video 

video1 = VideoFileClip('dualCheck.mp4')

### Video 

video2 = VideoFileClip('V2.mp4')

### Text 

_end =  TextClip("endingText", fontsize=70, color='white')
_end =  _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])

final = concatenate_videoclips([begin, video1, video2, end], method='compose')
final.write_videofile("result.mp4", fps=30)



