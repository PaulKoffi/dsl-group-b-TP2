# CinEditorML model Code
# Result Video Name: part4

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_texteExt = TextClip("Prise en main facile grace a notre extension qui est present", fontsize=70, color='white')
_texteExt = _texteExt.set_position('center').set_duration(4)
texteExt = CompositeVideoClip([_texteExt], size=[1920, 1080])

### Text 

_texteExt1 = TextClip("pour vous aider dans lecriture de votre montage video", fontsize=70, color='white')
_texteExt1 = _texteExt1.set_position('center').set_duration(4)
texteExt1 = CompositeVideoClip([_texteExt1], size=[1920, 1080])

### Text 

_texteExt2 = TextClip("Ainsi que dans la generation de la video en question", fontsize=70, color='white')
_texteExt2 = _texteExt2.set_position('center').set_duration(4)
texteExt2 = CompositeVideoClip([_texteExt2], size=[1920, 1080])

### Text 

_texteExt3 = TextClip("Notre devise  vous faciliter la vie", fontsize=70, color='white')
_texteExt3 = _texteExt3.set_position('center').set_duration(6)
texteExt3 = CompositeVideoClip([_texteExt3], size=[1920, 1080])


# CREATE FINAL VIDEO

final = concatenate_videoclips([texteExt, texteExt1, texteExt2, texteExt3], method='compose')

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)

audio_audioE = AudioFileClip("audioExtension.mp3")
audio_final = final.audio
if audio_final is None:
    compo = CompositeAudioClip([audio_audioE.set_start(0).volumex(1.5)])
else:
    compo = CompositeAudioClip([audio_final.volumex(1), audio_audioE.set_start(0).volumex(1.5)])
final = final.set_audio(compo)
final.write_videofile("part4.mp4", fps=30)

### REMOVE TEMP VIDEOS


