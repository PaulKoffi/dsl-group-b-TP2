# CinEditorML model Code
# Result Video Name: demo

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Video 

video1 = VideoFileClip('part1.mp4')

### Video 

video2 = VideoFileClip('part2.mp4')

### Video 

video3 = VideoFileClip('part3.mp4')

### Video 

video4 = VideoFileClip('part4.mp4')

### Video 

video5 = VideoFileClip('part5.mp4')

### Video 

video6 = VideoFileClip('part6.mp4')


# CREATE FINAL VIDEO

final = concatenate_videoclips([video1, video2, video3, video4, video5, video6], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("demo.mp4", fps=30)

### REMOVE TEMP VIDEOS


