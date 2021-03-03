# CinEditorML model Code
# Result Video Name: resultAudio

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

video1 = VideoFileClip('test3.mp4')

### Video 

video2 = VideoFileClip('video2.mp4')

### Text 

_end = TextClip("endingText", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])


### AUDIO 

TEMPV = 0 + 10 + 0
audio_audio1 = AudioFileClip("audio.mp3")
audio_video1 = video1.audio
compo = CompositeAudioClip([audio_video1.volumex(1), audio_audio1.set_start(TEMPV).volumex(1.5)])
video1 = video1.set_audio(compo)

# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, video1, video2, end], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)

audio_audio2 = AudioFileClip("audio.mp3")
audio_final = final.audio
compo = CompositeAudioClip([audio_final.volumex(1), audio_audio2.set_start(31).volumex(2.0)])
final = final.set_audio(compo)
final.write_videofile("resultAudio.mp4", fps=30)

### REMOVE TEMP VIDEOS


