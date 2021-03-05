# CinEditorML model Code
# Result Video Name: temporal

from moviepy.editor import *
import numpy as np
from moviepy.video.tools.segmenting import findObjects
from moviepy.video.io.VideoFileClip import VideoFileClip
import os

### Text 

_texteV = TextClip("CinEditorMl cest egalement du decoupage de videos", fontsize=70, color='white')
_texteV = _texteV.set_position('center').set_duration(10)
texteV = CompositeVideoClip([_texteV], size=[1920, 1080])

### Video 

videoMulti = VideoFileClip('MultiState.mp4')

### Text 

_texteV1 = TextClip("Nous allons decouper la video precedente en prenant", fontsize=70, color='white')
_texteV1 = _texteV1.set_position('center').set_duration(5)
texteV1 = CompositeVideoClip([_texteV1], size=[1920, 1080])

### Text 

_texteV2 = TextClip("respectivement les 11 premieres secondes et les 3 dernieres", fontsize=70, color='white')
_texteV2 = _texteV2.set_position('center').set_duration(5)
texteV2 = CompositeVideoClip([_texteV2], size=[1920, 1080])

### Text 

_texteV3 = TextClip("Nous allons egalement placer les sous titres ", fontsize=70, color='white')
_texteV3 = _texteV3.set_position('center').set_duration(5)
texteV3 = CompositeVideoClip([_texteV3], size=[1920, 1080])

### Text 

_texteV4 = TextClip("pour chaque seconde de la video", fontsize=70, color='white')
_texteV4 = _texteV4.set_position('center').set_duration(5)
texteV4 = CompositeVideoClip([_texteV4], size=[1920, 1080])

### Specific Video Part  

clip1 = videoMulti.subclip('00:00', '00:11')
clip1.write_videofile("clip1.mp4", fps=30)

### Specific Video Part  

clip1b = videoMulti.subclip('00:27', '00:30')
clip1b.write_videofile("clip1b.mp4", fps=30)

### Text 

_texteExt = TextClip("Prise en main facile grace a notre extension qui est present", fontsize=70, color='white')
_texteExt = _texteExt.set_position('center').set_duration(6)
texteExt = CompositeVideoClip([_texteExt], size=[1920, 1080])

### Text 

_texteExt1 = TextClip("pour vous aider dans lecriture de votre montage video", fontsize=70, color='white')
_texteExt1 = _texteExt1.set_position('center').set_duration(6)
texteExt1 = CompositeVideoClip([_texteExt1], size=[1920, 1080])

### Text 

_texteExt2 = TextClip("Ainsi que dans la generation de la video en question", fontsize=70, color='white')
_texteExt2 = _texteExt2.set_position('center').set_duration(5)
texteExt2 = CompositeVideoClip([_texteExt2], size=[1920, 1080])

### Text 

_texteExt3 = TextClip("Notre devise  vous faciliter la vie", fontsize=70, color='white')
_texteExt3 = _texteExt3.set_position('center').set_duration(5)
texteExt3 = CompositeVideoClip([_texteExt3], size=[1920, 1080])


### AUDIO 

TEMPV = 0 + 0
audio_audioD = AudioFileClip("audioDecoupage.mp3")
audio_texteV = texteV.audio
if audio_texteV is None:
    compo = CompositeAudioClip([audio_audioD.set_start(TEMPV).volumex(1.5)])
else:
    compo = CompositeAudioClip([audio_texteV.volumex(1), audio_audioD.set_start(TEMPV).volumex(1.5)])
texteV = texteV.set_audio(compo)


TEMPV = 0 + 10 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 1
sV1 = TextClip("Alors ici 4eme scenario ", fontsize=70, color='white')
sV1 = sV1.set_position(('center','bottom')).set_duration(2)
sV1 = sV1.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 4
sV2 = TextClip("Le multiStateAlarm on demarre la simulation ", fontsize=70, color='white')
sV2 = sV2.set_position(('center','bottom')).set_duration(3)
sV2 = sV2.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + 7
sV3 = TextClip("Appuie sur le bouton alarme allumee ", fontsize=70, color='white')
sV3 = sV3.set_position(('center','bottom')).set_duration(3)
sV3 = sV3.set_start(TEMPV)


TEMPV = 0 + 10 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + int(clip1.duration) + 0
sV4 = TextClip("Etat initial ", fontsize=70, color='white')
sV4 = sV4.set_position(('center','bottom')).set_duration(3)
sV4 = sV4.set_start(TEMPV)

### AUDIO 

TEMPV = 0 + 10 + int(videoMulti.duration) + 5 + 5 + 5 + 5 + int(clip1.duration) + int(clip1b.duration) + 0
audio_audioE = AudioFileClip("audioExtension.mp3")
audio_texteExt = texteExt.audio
if audio_texteExt is None:
    compo = CompositeAudioClip([audio_audioE.set_start(TEMPV).volumex(1.5)])
else:
    compo = CompositeAudioClip([audio_texteExt.volumex(1), audio_audioE.set_start(TEMPV).volumex(1.5)])
texteExt = texteExt.set_audio(compo)

# CREATE FINAL VIDEO

final = concatenate_videoclips([texteV, videoMulti, texteV1, texteV2, texteV3, texteV4, clip1, clip1b, texteExt, texteExt1, texteExt2, texteExt3], method='compose')
final = CompositeVideoClip([final, sV1, sV2, sV3, sV4], size=[1920, 1080])

### SET ABSOLUTE AUDIO IF EXIST (AUDIO ON GLOBAL VIDEO)
final.write_videofile("temporal.mp4", fps=30)

### REMOVE TEMP VIDEOS
os.remove('clip1.mp4')
os.remove('clip1b.mp4')


