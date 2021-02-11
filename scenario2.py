from moviepy.editor import *
from moviepy.video.io.VideoFileClip import VideoFileClip

myvideoOne = VideoFileClip("atletico_celta.mp4").subclip(23,107)
myvideoTwo = VideoFileClip("atletico_celta.mp4").subclip(121,141)

_txt_begin=TextClip("my title text goes here..", fontsize=70, color='white')
_txt_begin=_txt_begin.set_position('center').set_duration(10)

_txt_begin_1 = TextClip("my title text goes here..", fontsize=70, color='white')
_txt_begin_1 =_txt_begin_1.set_position('center').set_duration(10)
_txt_begin_1 = _txt_begin_1.set_start(40)

_txt_begin_2 = TextClip("my title text goes here..", fontsize=70, color='white')
_txt_begin_2 =_txt_begin_2.set_position('center').set_duration(15)
_txt_begin_2 = _txt_begin_2.set_start(-5)

secondclip = CompositeVideoClip([myvideoTwo,_txt_begin_2],size=myvideoOne.size)
txt_begin = CompositeVideoClip( [myvideoOne,_txt_begin,_txt_begin_1], size=myvideoOne.size)

final = concatenate_videoclips([txt_begin,secondclip],method='compose')
final.write_videofile("videoscenario2.mp4", fps=30)