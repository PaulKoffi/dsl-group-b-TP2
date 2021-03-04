# CinEditorML model Code
# Result Video Name: resultEnding

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

def vortex(screenpos, i, nletters):
    d = lambda t: 1.0 / (0.3 + t ** 8)  # damping
    a = i * np.pi / nletters  # angle of the movement
    v = rotMatrix(a).dot([-1, 0])
    if i % 2: v[1] = -v[1]
    return lambda t: screenpos + 400 * d(t) * rotMatrix(0.5 * d(t) * a).dot(v)

blackmask = ImageClip('image_mountain.jpg').resize(0.2).set_duration(10)
w = 720
h = w * 9 / 16 
moviesize = w, h

txt = "ending credits, credit1, credit2, credit3".replace(",", "\n")
# Add blanks
txt = 10 * "\n" + txt + 10 * "\n"

begin = TextClip(txt, color='white', align='West', fontsize=25,
                    font='Xolonium-Bold', method='label')
begin = begin.set_position(lambda t: ('center', (-20 * (t + 5)))).set_duration(10)

begin = CompositeVideoClip([blackmask, begin.crossfadein(2)])

### TextClip with Animation 

_end = TextClip("end", fontsize=70, color='white')
_end = _end.set_position('center').set_duration(10)
end = CompositeVideoClip([_end], size=[1920, 1080])
letters = findObjects(end)
end = CompositeVideoClip(moveLetters(letters, vortex), size=[1920, 1080]).subclip(0, 10)


# CREATE FINAL VIDEO

final = concatenate_videoclips([begin, end], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("resultEnding.mp4", fps=30)

### REMOVE TEMP VIDEOS


