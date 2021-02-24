# CinEditorML model Code
# Result Video Name: result1

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

# helper function
rotMatrix = lambda a: np.array([[np.cos(a), np.sin(a)],
                                [-np.sin(a), np.cos(a)]])


# WE ANIMATE THE LETTERS
def moveLetters(letters, funcpos):
    return [letter.set_pos(funcpos(letter.screenpos, i, len(letters)))
            for i, letter in enumerate(letters)]

def arrive(screenpos, i, nletters):
    v = np.array([-1, 0])
    d = lambda t: max(0, 3 - 3 * t)
    return lambda t: screenpos - 400 * v * d(t - 0.2 * i)

### TextClip with Animation 

_begin = TextClip("beginningText", fontsize=70, color='white')
_begin = _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])
letters = findObjects(begin)
begin = CompositeVideoClip(moveLetters(letters, arrive), size=[1920, 1080]).subclip(0, 10)

### Video 

video1 = VideoFileClip('test3.mp4')

### Video 

video2 = VideoFileClip('va2.mp4')

### Text 

_end = TextClip("endingText", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])


### AUDIO 

TEMPV = 0 + int(begin.duration) + 0
audio_audio1 = AudioFileClip("audio.mp3")
audio_video1 = video1.audio
compo = CompositeAudioClip([audio_video1.volumex(1.5), audio_audio1.set_start(TEMPV)])
video1 = video1.set_audio(compo)

# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, video1, video2, end], method='compose')
final.write_videofile("result1.mp4", fps=30)


### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)

final = VideoFileClip('result1.mp4')
audio_audio2 = AudioFileClip("audio.mp3")
audio_final = final.audio
compo = CompositeAudioClip([audio_final.volumex(2.0), audio_audio2.set_start(45)])
final = final.set_audio(compo)
final.write_videofile("result1.mp4", fps=30)

### REMOVE TEMP VIDEOS


