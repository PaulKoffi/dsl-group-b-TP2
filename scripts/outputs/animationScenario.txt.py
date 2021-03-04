# CinEditorML model Code
# Result Video Name: resultAnimation

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

def vortex(screenpos, i, nletters):
    d = lambda t: 1.0 / (0.3 + t ** 8)  # damping
    a = i * np.pi / nletters  # angle of the movement
    v = rotMatrix(a).dot([-1, 0])
    if i % 2: v[1] = -v[1]
    return lambda t: screenpos + 400 * d(t) * rotMatrix(0.5 * d(t) * a).dot(v)

### TextClip with Animation 

_begin = TextClip("start", fontsize=70, color='white')
_begin = _begin.set_position('center').set_duration(10)
begin = CompositeVideoClip([_begin], size=[1920, 1080])
letters = findObjects(begin)
begin = CompositeVideoClip(moveLetters(letters, arrive), size=[1920, 1080]).subclip(0, 10)

### Video 

video1 = VideoFileClip('videoTest1.mp4')

### Video 

video2 = VideoFileClip('videoTest2.mp4')

### TextClip with Animation 

_end = TextClip("end", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])
letters = findObjects(end)
end = CompositeVideoClip(moveLetters(letters, vortex), size=[1920, 1080]).subclip(0, 10)


# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, video1, video2, end], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("resultAnimation.mp4", fps=30)

### REMOVE TEMP VIDEOS


