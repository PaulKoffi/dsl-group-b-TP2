from moviepy.editor import *
from moviepy.video.io.VideoFileClip import VideoFileClip

# my_video_One = VideoFileClip("atletico_celta.mp4").subclip(37,100).subclip(20,35)
# my_video_One = VideoFileClip("test.mp4").subclip(5, 20)
# my_video_One.write_videofile("myvideoone.mp4", fps=30)
# my_video_two = VideoFileClip("myvideoone.mp4").subclip(4,13)
my_video_two = VideoFileClip("atletico_celta.mp4").subclip('02:21','02:41')

# #Texte 1
# _txt_begin=TextClip("this is the beginning", fontsize=70, color='white')
# _txt_begin=_txt_begin.set_position('center').set_duration(10)
# txt_begin = CompositeVideoClip( [_txt_begin], size=my_video_One.size)

# #Texte 2
# _txt_end=TextClip("this is the end..", fontsize=70, color='white')
# _txt_end=_txt_end.set_position('center').set_duration(10)
# #why do we need to do this?  I don't know, but it works..
# txt_end = CompositeVideoClip( [_txt_end], size=my_video_One.size)

# _txt_sub_1=TextClip("my title text goes here..", fontsize=70, color='white')
# _txt_sub_1=_txt_sub_1.set_position('center').set_duration(10)

# _txt_sub_2 = TextClip("my title text goes here..", fontsize=70, color='white')
# _txt_sub_2 =_txt_sub_2.set_position('center').set_duration(10)
# _txt_sub_2 = _txt_sub_2.set_start(40)

# _txt_sub_3 = TextClip("my title text goes here..", fontsize=70, color='white')
# _txt_sub_3 =_txt_sub_3.set_position('center').set_duration(15)
# _txt_sub_3 = _txt_sub_3.set_start(-5)

# first_clip = CompositeVideoClip( [my_video_One,_txt_sub_1,_txt_sub_2], size=my_video_One.size)
# second_clip = CompositeVideoClip([my_video_Two,_txt_sub_3],size=my_video_One.size)

# final = concatenate_videoclips([txt_begin,first_clip,second_clip,txt_end],method='compose')
my_video_two.write_videofile("videoscenario4.mp4", fps=30)