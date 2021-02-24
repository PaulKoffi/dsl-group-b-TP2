from moviepy import editor
import os.path as op
from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

def annotate(clip, txt, txt_color='red', fontsize=50, font='Xolonium-Bold'):
    """ Writes a text at the bottom of the clip. """
    txtclip = editor.TextClip(txt, fontsize=fontsize, font=font, color=txt_color)
    cvc = editor.CompositeVideoClip([clip, txtclip.set_pos(('center', 'bottom'))])
    return cvc.set_duration(clip.duration)

clip1 = VideoFileClip('V5.mp4')
clip1b = clip1.subclip('02:01', '02:21')
clip1b.write_videofile("clip1b.mp4", fps=30)

annotated_clips = [annotate(clip1b, "ceci est un text")]
final_clip = editor.concatenate_videoclips(annotated_clips)
final_clip.write_videofile("text.mp4", fps=30)


A ___ B

temp == d
temp == f

____ ==>  0 d
==> d f
===> f ___ int()

=====> UUUU