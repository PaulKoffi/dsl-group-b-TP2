from moviepy.editor import *

"""
N'oubliez pas de changer le nom des fichiers videos
"""

video_two = VideoFileClip("coolTextEffects.avi")

video_one = VideoFileClip("coolTextEffects.mp4")

video_three = VideoFileClip('test.mp4')

_txt_begin=TextClip("my title text goes here..", fontsize=70, color='white')
_txt_begin=_txt_begin.set_position('center').set_duration(10)

_txt_end=TextClip("this is the end..", fontsize=70, color='white')
_txt_end=_txt_end.set_position('center').set_duration(10)

#why do we need to do this?  I don't know, but it works..
txt_begin = CompositeVideoClip( [_txt_begin], size=video_one.size)

txt_end = CompositeVideoClip( [_txt_end], size=video_one.size)

#video is a VideoFileClip I've already read in..  # video=VideoFileClip("myvideo.mp4")
final = concatenate_videoclips([txt_begin, video_one,video_three, video_two, txt_end],method='compose')
final.write_videofile("videoscenario1.mp4", fps=30)